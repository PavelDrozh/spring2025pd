package org.example.service;

import org.example.model.Question;
import org.example.repository.QuestionsRepo;
import org.example.util.LocalizationService;
import org.example.util.StreamsIOService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.example.util.LocalizationServiceImpl.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestingServiceImplTest {

    @InjectMocks
    TestingServiceImpl service;
    @Mock
    LocalizationService localizationService;
    @Mock
    StreamsIOService ioService;
    @Mock
    QuestionsRepo repo;

    private Question mockQuestion2;
    private Question mockQuestion1;

    @BeforeEach
    void setUp() {
        mockQuestion1 = mock(Question.class);
        when(mockQuestion1.getText()).thenReturn("What is the capital of Russia?");
        when(mockQuestion1.getOptions()).thenReturn(Arrays.asList("Moscow", "Saint Petersburg"));

        mockQuestion2 = mock(Question.class);
        when(mockQuestion2.getText()).thenReturn("Who wrote War and Peace?");
        when(mockQuestion2.getOptions()).thenReturn(Arrays.asList("Tolstoy", "Dostoevsky"));

        when(repo.getAll()).thenReturn(List.of(mockQuestion1, mockQuestion2));
    }

    @Test
    void shouldPrintAllQuestions() {
        //Arrange
        when(localizationService.getMessage(eq(QUESTIONS_TOTAL), any(Integer.class))).thenReturn("Всего вопросов: %d");
        when(localizationService.getMessage(eq(QUESTIONS_DOT), any(Integer.class), any(String.class)))
                .thenReturn("%s. %s");
        // Act
        service.printQuestions();

        // Assert
        verify(mockQuestion1).getText();
        verify(mockQuestion1, atLeastOnce()).getOptions();
        verify(mockQuestion2).getText();
        verify(mockQuestion2, atLeastOnce()).getOptions();
    }

    @Test
    void shouldHandleUserInputForTest() {
        // Arrange
        when(localizationService.getMessage(TEST_WELCOME)).thenReturn("Добро пожаловать на тестирование.");
        when(localizationService.getMessage(TEST_LAST)).thenReturn("Введите вашу фамилию:");
        when(localizationService.getMessage(eq(TEST_NUMBERS), eq("Иванов"), eq("Иван")))
                .thenReturn("Тест начинается, Иванов Иван.");
        when(localizationService.getMessage(TEST_SIMPLE)).thenReturn("Начинайте отвечать.");
        when(localizationService.getMessage(anyString(), anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn("Вы ответили правильно на 2 вопроса из 2.");
        when(localizationService.getMessage(eq("test.excellent"), eq("Иван"))).thenReturn("Отлично справились, Иван!");
        when(mockQuestion1.getCorrectAnswer()).thenReturn(1); // Correct answer is Moscow
        when(mockQuestion2.getCorrectAnswer()).thenReturn(1); // Correct answer is Tolstoy
        when(localizationService.getMessage(eq(QUESTIONS_DOT), any(Integer.class), any(String.class)))
                .thenReturn("%s. %s");
        when(ioService.readString())
                .thenReturn("Иван")
                .thenReturn("Иванов")
                .thenReturn("")
                .thenReturn("1")   // Ответ на первый вопрос
                .thenReturn("1");  // Ответ на второй вопрос

        // Act
        service.startTest();

        // Assert
        verify(mockQuestion1).getText();
        verify(mockQuestion1, atLeastOnce()).getOptions();
        verify(mockQuestion2).getText();
        verify(mockQuestion2, atLeastOnce()).getOptions();
    }
}
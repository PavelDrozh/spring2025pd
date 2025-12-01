package org.example.service;

import org.example.model.Question;
import org.example.repository.QuestionsRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestingServiceImplTest {

    @InjectMocks
    TestingServiceImpl service;

    @Mock
    QuestionsRepo repo;

    private InputStream originalIn;
    private Question mockQuestion2;
    private Question mockQuestion1;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
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
        when(mockQuestion1.getCorrectAnswer()).thenReturn(1); // Correct answer is Moscow
        when(mockQuestion2.getCorrectAnswer()).thenReturn(1); // Correct answer is Tolstoy

        // Mock user input (name, surname, and answers)
        String input = "John\nDoe\n\n1\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        service.startTest();

        // Assert
        verify(mockQuestion1).getText();
        verify(mockQuestion1, atLeastOnce()).getOptions();
        verify(mockQuestion2).getText();
        verify(mockQuestion2, atLeastOnce()).getOptions();
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }
}
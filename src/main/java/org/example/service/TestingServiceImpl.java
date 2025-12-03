package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Question;
import org.example.repository.QuestionsRepo;
import org.example.util.IOService;
import org.example.util.LocalizationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.util.LocalizationServiceImpl.*;

@Service
@AllArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final QuestionsRepo questionsRepo;
    private final LocalizationService localizationService;
    private final IOService ioService;

    @Override
    public void printQuestions() {
        List<Question> questions = questionsRepo.getAll();
        ioService.outputString(localizationService.getMessage(QUESTIONS_TOTAL,questions.size()));
        for (Question q : questions) {
            printQuestion(q);
            ioService.outputString(""); // Add an empty line between questions
        }
    }

    private void printQuestion(Question q) {
        ioService.outputString(q.getText());
        if (!q.getOptions().isEmpty()) {
            int i = 1;
            for (String opt : q.getOptions()) {
                ioService.outputString(localizationService.getMessage(QUESTIONS_DOT,i++,opt));
            }
        }
    }

    @Override
    public void startTest() {
        ioService.outputString(localizationService.getMessage(TEST_WELCOME));
        String name = ioService.readString();
        ioService.outputString(localizationService.getMessage(TEST_LAST));
        String lastName = ioService.readString();
        ioService.outputString(localizationService.getMessage(TEST_NUMBERS,lastName, name));
        ioService.outputString(localizationService.getMessage(TEST_SIMPLE));
        ioService.readString();

        List<Question> questions = questionsRepo.getAll();
        int correctAnswers = 0;
        for (Question question : questions) {
            printQuestion(question);
            int answer = 0;
            boolean notNumber = true;
            while (notNumber) {
                try {
                    answer = Integer.parseInt(ioService.readString());
                    notNumber = false;
                } catch (NumberFormatException e) {
                    ioService.outputString(localizationService.getMessage(TEST_PARSE_ERR));
                }
            }
            if (answer == question.getCorrectAnswer()) {
                correctAnswers++;
            }
        }
        ioService.outputString(localizationService.getMessage(TEST_SCORE, lastName, name, correctAnswers, questions.size()));
        if (correctAnswers == questions.size()) {
            ioService.outputString(localizationService.getMessage(TEST_EXCELLENT, name));
        } else {
            ioService.outputString(localizationService.getMessage(TEST_GOOD, name));
        }
    }
}

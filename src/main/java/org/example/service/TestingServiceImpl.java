package org.example.service;

import org.example.model.Question;
import org.example.repository.QuestionsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TestingServiceImpl implements TestingService {

    private final QuestionsRepo questionsRepo;

    public TestingServiceImpl(QuestionsRepo questionsRepo) {
        this.questionsRepo = questionsRepo;
    }

    @Override
    public void printQuestions() {
        List<Question> questions = questionsRepo.getAll();
        System.out.println("Total Questions: " + questions.size());
        for (Question q : questions) {
            printQuestion(q);
            System.out.println(); // Add an empty line between questions
        }
    }

    private void printQuestion(Question q) {
        System.out.println(q.getText());
        if (!q.getOptions().isEmpty()) {
            int i = 1;
            for (String opt : q.getOptions()) {
                System.out.println(i++ + ". " + opt);
            }
        }
    }

    @Override
    public void startTest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to test! Let's get acquainted, please write your name");
        String name = sc.nextLine();
        System.out.println("And your last name");
        String lastName = sc.nextLine();
        System.out.printf("So, %s %s, you need write only numbers of answers and then press \"Enter\" %n", lastName, name);
        System.out.println("It's simple, so when you're ready - press \"Enter\"");
        sc.nextLine();

        List<Question> questions = questionsRepo.getAll();
        int correctAnswers = 0;
        for (Question question : questions) {
            printQuestion(question);
            int answer = 0;
            boolean notNumber = true;
            while (notNumber) {
                try {
                    answer = Integer.parseInt(sc.nextLine());
                    notNumber = false;
                } catch (NumberFormatException e) {
                    System.out.println("Parse error, please write only number of answer");
                }
            }
            if (answer == question.getCorrectAnswer()) {
                correctAnswers++;
            }
        }
        System.out.printf("Your score, %s %s: %d / %d %n", lastName, name, correctAnswers, questions.size());
        if (correctAnswers == questions.size()) {
            System.out.printf("Excellent, %s! %n", name);
        } else {
            System.out.printf("Good, but you can better, %s! %n", name);
        }
        sc.close();
    }
}

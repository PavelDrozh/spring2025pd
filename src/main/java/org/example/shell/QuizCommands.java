package org.example.shell;

import org.example.service.TestingService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class QuizCommands {

    private final TestingService testingService;

    public QuizCommands(TestingService testingService) {
        this.testingService = testingService;
    }

    @ShellMethod(key = "start", value = "Start the quiz")
    public void start() {
        testingService.startTest();
    }

    @ShellMethod(key = "print-questions", value = "Print all questions")
    public void printQuestions() {
        testingService.printQuestions();
    }
}

package org.example;

import org.example.repository.QuestionsRepoImpl;
import org.example.service.TestingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsRepoImpl questionsRepo = context.getBean(QuestionsRepoImpl.class);
        questionsRepo.init();
        TestingService testingService = context.getBean(TestingService.class);
        testingService.printQuestions();
    }
}
package org.example;

import org.example.service.TestingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApp.class);

        TestingService testingService = context.getBean(TestingService.class);
        testingService.startTest();
    }
}
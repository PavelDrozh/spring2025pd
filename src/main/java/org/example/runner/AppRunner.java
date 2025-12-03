package org.example.runner;

import org.example.service.TestingService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final TestingService testingService;

    public AppRunner(TestingService testingService) {
        this.testingService = testingService;
    }

    @Override
    public void run(ApplicationArguments args) {
        testingService.startTest();
    }
}
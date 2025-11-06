package org.example.service;

import org.example.model.Question;
import org.example.repository.QuestionsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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
            System.out.println(q.getText());
            if (!q.getOptions().isEmpty()) {
                int i = 1;
                for (String opt : q.getOptions()) {
                    System.out.println(i++ + ". " + opt);
                }
            }
            System.out.println(); // Add an empty line between questions
        }
    }
}

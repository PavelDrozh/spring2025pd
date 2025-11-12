package org.example.repository;

import org.example.model.Question;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionsRepoImpl implements QuestionsRepo{
    private final Resource resource;

    private List<Question> questions;

    public QuestionsRepoImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }

    public void init() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            questions = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) { // At least question, answer option(s), type of question
                    String questionText = parts[0];
                    List<String> options = Arrays.asList(parts).subList(1, parts.length - 1);
                    boolean isMultipleChoice = Boolean.parseBoolean(parts[parts.length - 1]);
                    questions.add(new Question(questionText, options, isMultipleChoice));
                }
            }
        } catch (IOException e) {
            System.out.println("Some problem with resource reading");
            throw new RuntimeException(e);
        }
    }
}

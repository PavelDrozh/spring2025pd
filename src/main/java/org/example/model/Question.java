package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String text;
    private List<String> options;
    private boolean isMultipleChoice;
    private int correctAnswer;

    public Question(String text, List<String> options, boolean isMultipleChoice, int correctAnswer) {
        this.text = text;
        this.options = options;
        this.isMultipleChoice = isMultipleChoice;
        this.correctAnswer = correctAnswer;
    }
}
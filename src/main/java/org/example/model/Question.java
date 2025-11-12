package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String text;
    private List<String> options;
    private boolean isMultipleChoice;

    public Question(String text, List<String> options, boolean isMultipleChoice) {
        this.text = text;
        this.options = options;
        this.isMultipleChoice = isMultipleChoice;
    }
}
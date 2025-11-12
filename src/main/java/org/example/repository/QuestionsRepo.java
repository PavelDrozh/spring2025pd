package org.example.repository;

import org.example.model.Question;

import java.util.List;

public interface QuestionsRepo {
    List<Question> getAll();
}

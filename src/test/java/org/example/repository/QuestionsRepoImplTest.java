package org.example.repository;

import org.example.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(value = MockitoExtension.class)
class QuestionsRepoImplTest {

    QuestionsRepoImpl repo;
    Resource resource = new ClassPathResource("questions.csv");

    @BeforeEach
    public void setUp() {
        repo = new QuestionsRepoImpl(resource);
        repo.init();
    }

    @Test
    void testGetAll() {
        List<Question> questions = repo.getAll();
        assertEquals(5, questions.size());
    }
}
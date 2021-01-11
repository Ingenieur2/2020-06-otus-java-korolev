package ru.package01.core.service;

import ru.package01.core.model.Question;

import java.util.List;
import java.util.Optional;

public interface DbServiceQuestion {
    long saveQuestion(Question user);

    Optional<Question> getQuestion(long id);

    List<Question> getAll();
}

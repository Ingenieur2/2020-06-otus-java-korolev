package ru.package01.core.service;

import ru.package01.core.model.Answer;

import java.util.List;
import java.util.Optional;

public interface DbServiceAnswer {
    long saveAnswer(String answerString);

    Optional<Answer> getAnswer(long id);

    List<Answer> getAnswersForThisQuestion(long id);

    List<Answer> getAll();
}

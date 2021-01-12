package ru.package01.core.service;

public interface DbServiceAnswer {
    long saveAnswer(String userString);

    String[] checkAnswer(String answerString);
}

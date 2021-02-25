package ru.package01.core.service;

public interface CheckService {
    long saveUserAnswer(String userAnswerString);
    String[] checkAnswer(String userAnswerString);
}

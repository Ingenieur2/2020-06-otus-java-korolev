package ru.package01.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}

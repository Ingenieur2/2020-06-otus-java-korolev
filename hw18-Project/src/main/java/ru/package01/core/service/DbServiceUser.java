package ru.package01.core.service;

import ru.package01.core.model.User;

import java.util.List;
import java.util.Optional;

public interface DbServiceUser {
    long saveUser(String userString);

    long checkUser(String userString);

    Optional<User> getUser(long id);

    List<User> getAll();
}

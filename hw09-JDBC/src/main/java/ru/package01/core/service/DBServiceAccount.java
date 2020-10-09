package ru.package01.core.service;

import ru.package01.core.model.Account;

import java.util.Optional;

public interface DBServiceAccount {

    long saveAccount(Account account);

    Optional<Account> getAccount(long id);
}

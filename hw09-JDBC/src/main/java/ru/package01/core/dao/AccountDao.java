package ru.package01.core.dao;

import ru.package01.core.model.Account;
import ru.package01.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> findById(long id);

    long insertAccount(Account account);

    SessionManager getSessionManager();
}

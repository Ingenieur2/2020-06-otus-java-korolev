package ru.package01.jdbc.mapper;

import ru.package01.core.sessionmanager.SessionManager;

import java.util.Optional;

/**
 * Saving object into base, read object from base
 * @param <T>
 */
public interface JdbcMapper<T> {
    long insert(T objectData);

    void update(T objectData);

    void insertOrUpdate(T objectData);

    Optional<T> findById(Object id);

    SessionManager getSessionManager();
}

package ru.package01.jdbc.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.dao.UserDaoException;
import ru.package01.jdbc.DbExecutorImpl;
import ru.package01.core.model.User;
import ru.package01.core.sessionmanager.SessionManager;
import ru.package01.jdbc.sessionmanager.SessionManagerJdbc;

// этот класс не должен быть в домашней работе
public class UserDaoJdbc implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutorImpl<User> dbExecutor;

    public UserDaoJdbc(SessionManagerJdbc sessionManager, DbExecutorImpl<User> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return dbExecutor.executeSelect(getConnection(), "select id, name from user where id  = ?",
                    id, rs -> {
                        try {
                            if (rs.next()) {
                                return new User(rs.getLong("id"), rs.getString("name"));
                            }
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                        return null;
                    });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long insertUser(User user) {
        try {
            return dbExecutor.executeInsert(getConnection(), "insert into user(name) values (?)",
                    Collections.singletonList(user.getName()));
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}

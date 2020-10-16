package ru.package01.jdbc.dao;

import ru.package01.core.dao.UserDao;
import ru.package01.core.model.User;
import ru.package01.core.sessionmanager.SessionManager;
import ru.package01.jdbc.mapper.JdbcMapper;

import java.util.Optional;

public class UserDaoJdbcMapper implements UserDao {

    private final JdbcMapper<User> jdbcMapper;

    public UserDaoJdbcMapper(JdbcMapper<User> jdbcMapper) {
        this.jdbcMapper = jdbcMapper;
    }

    @Override
    public Optional<User> findById(long id) {
        return jdbcMapper.findById(id);
    }

    @Override
    public long insertUser(User user) {
        return jdbcMapper.insert(user);
    }

    @Override
    public SessionManager getSessionManager() {
        return jdbcMapper.getSessionManager();
    }
}

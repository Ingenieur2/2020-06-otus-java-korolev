package ru.package01.jdbc.dao;

import ru.package01.core.dao.AccountDao;
import ru.package01.core.model.Account;
import ru.package01.core.sessionmanager.SessionManager;
import ru.package01.jdbc.mapper.JdbcMapper;

import java.util.Optional;

public class AccountDaoJdbcMapper implements AccountDao {

    private final JdbcMapper<Account> jdbcMapper;

    public AccountDaoJdbcMapper(JdbcMapper<Account> jdbcMapper) {
        this.jdbcMapper = jdbcMapper;
    }

    @Override
    public Optional<Account> findById(long id) {
        return jdbcMapper.findById(id);
    }

    @Override
    public long insertAccount(Account account) {
        return jdbcMapper.insert(account);
    }

    @Override
    public SessionManager getSessionManager() {
        return jdbcMapper.getSessionManager();
    }
}

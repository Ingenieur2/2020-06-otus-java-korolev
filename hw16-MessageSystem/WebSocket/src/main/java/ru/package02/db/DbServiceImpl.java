package ru.package02.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.model.User;

import java.util.HashMap;
import java.util.Map;

public class DbServiceImpl implements DbService {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);
    private final Map<Long, String> database = new HashMap<>();

    public DbServiceImpl(User user) {
        database.put(user.getId(), user.toString());
    }

    public String getUserData(long id) {
        logger.info("get data for id:{}", id);
        return database.get(id);
    }

}

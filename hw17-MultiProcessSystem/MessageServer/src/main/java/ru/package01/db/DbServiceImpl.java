package ru.package01.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DbServiceImpl implements DbService {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);
    private final Map<Long, String> database = new HashMap<>();
    private final LinkedList<String> helperList = new LinkedList<>();

    public DbServiceImpl(String data) {

        helperList.addLast(data);
        database.put((long) helperList.indexOf(data), data);
    }

    public String getUserData(long id) {
        id = helperList.indexOf(helperList.getLast());
        logger.info("get data for id:{}", id);
        return database.get(id);
    }
}

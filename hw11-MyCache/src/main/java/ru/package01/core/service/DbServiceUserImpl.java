package ru.package01.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.User;
import ru.package01.mycache.HwCache;
import ru.package01.mycache.MyCache;

import java.util.*;

public class DbServiceUserImpl implements DBServiceUser {

    private static final Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);
    private final UserDao userDao;
    HwCache<Long, User> myCache = new MyCache<>();

    public DbServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {

        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var userId = userDao.insertUser(user);
                sessionManager.commitSession();
                logger.info("created user: {}", userId);
                return userId;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> getUser(long id) {

        long beginTime = System.currentTimeMillis();
        System.out.println("ID = " + id);
        System.out.println("element from myCache:  " + myCache.get(id));
        if (myCache.get(id) != null) {

            logger.info("getValue:{}", myCache.get(id));
            User user = myCache.get(id);

            Optional<User> userOptional = Optional.ofNullable(user);
            System.out.println("time to get from CACHE:" + (System.currentTimeMillis() - beginTime) + " millis");
            return userOptional;
        } else {

            beginTime = System.currentTimeMillis();

            try (var sessionManager = userDao.getSessionManager()) {
                sessionManager.beginSession();
                try {
                    Optional<User> userOptional = userDao.findById(id);
                    logger.info("user: {}", userOptional.orElse(null));
                    System.out.println("time to get from DataBase:" + (System.currentTimeMillis() - beginTime) + " millis");

                    myCache.put(id, userOptional.get());
                    return userOptional;
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    sessionManager.rollbackSession();
                }
                return Optional.empty();
            }
        }
    }
}

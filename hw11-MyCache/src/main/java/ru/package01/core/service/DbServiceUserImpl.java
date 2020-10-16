package ru.package01.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.User;
import ru.package01.mycache.HwListener;

import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class DbServiceUserImpl implements DBServiceUser {

    private static final Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);
    private final UserDao userDao;
    Map<Long, User> myCache = new WeakHashMap<Long, User>();

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

                myCache.put(userId, user);

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

        if (myCache.containsKey(id)) {

            logger.info("getValue:{}", myCache.get(id));
            Optional<User> userOptional = Optional.ofNullable(myCache.get(id));

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

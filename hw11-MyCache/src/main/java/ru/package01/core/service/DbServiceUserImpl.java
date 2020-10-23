package ru.package01.core.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.User;
import ru.package01.mycache.HwListener;
import ru.package01.mycache.MyCache;

import java.util.*;


public class DbServiceUserImpl implements DBServiceUser {

    private static final Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);
    private final UserDao userDao;
    MyCache<Long, User> myCache;
    HwListener<Long, User> listener;


    public DbServiceUserImpl(UserDao userDao, MyCache<Long, User> myCache, HwListener<Long, User> listener) {
        this.userDao = userDao;
        this.myCache = myCache;
        this.listener = listener;
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
        Gson gson = new Gson();

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
                    try {
                        myCache.put(id, userOptional.get());
                        String json = gson.toJson(myCache);
                        System.out.println("current state after adding element is:  " + json);
                    } catch (Exception ex) {
                        myCache.put(id, null);
                    }
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

package ru.package01.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.package01.core.model.User;
import ru.package01.core.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceUserImpl implements DbServiceUser {

    private static final Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final UserRepository userRepository;

    public DbServiceUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long saveUser(User user) {
        try {
            if ((userRepository.findByLogin(user.getLogin()).isEmpty())
                    && !user.getLogin().equals("")
                    && !user.getPassword().equals("")
            ) {
                logger.info("created user:_____");
                userRepository.save(user);
            }

            long userId = user.getId();
            logger.info("created user: {}", userId);
            return userId;
        } catch (Exception e) {
            System.out.println("DID NOT CREATED");
            throw new DbServiceException(e);
        }
    }

    @Override
    public long checkUser(User user) {
        try {
            if (userRepository.findByLogin(user.getLogin()).get(0).getId() ==
                    userRepository.findByPassword(user.getPassword()).get(0).getId()) {
                logger.info("--LOGIN SUCCESSFUL--");
            }

            long userId = userRepository.findByLogin(user.getLogin()).get(0).getId();
            logger.info("found user with id= {}", userId);
            return userId;
        } catch (Exception e) {
            System.out.println("DID NOT FIND");
            throw new DbServiceException(e);
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            logger.info("user: {}", userOptional.orElse(null));
            return userOptional;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }
}

package ru.package01;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceUserImpl;
import ru.package01.h2.DataSourceH2;
import ru.package01.jdbc.DbExecutorImpl;
import ru.package01.jdbc.dao.UserDaoJdbcMapper;
import ru.package01.jdbc.mapper.EntityClassMetaDataImpl;
import ru.package01.jdbc.mapper.EntitySQLMetaDataImpl;
import ru.package01.jdbc.mapper.JdbcMapperImpl;
import ru.package01.jdbc.sessionmanager.SessionManagerJdbc;
import ru.package01.mycache.HwCache;
import ru.package01.mycache.HwListener;
import ru.package01.mycache.MyCache;

import javax.sql.DataSource;
import java.util.Optional;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
        HwCache<Long, User> myCache = new MyCache<>();

        // пример, когда Idea предлагает упростить код, при этом может появиться "спец"-эффект
        HwListener<Long, User> listener = new HwListener<Long, User>() {
            @Override
            public void notify(Long key, User value, String action) {
                logger.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };
        myCache.addListener(listener);

        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Work with user
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();

        var entityClassMetaData = new EntityClassMetaDataImpl<User>(User.class);
        var entitySQLMetaData = new EntitySQLMetaDataImpl<User>(entityClassMetaData);
        var jdbcMapperUser = new JdbcMapperImpl<User>(sessionManager, dbExecutor, entitySQLMetaData, entityClassMetaData);

        UserDao userDao = new UserDaoJdbcMapper(jdbcMapperUser);
        var dbServiceUser = new DbServiceUserImpl(userDao);
        for (int i = 1; i < 5; i++) {
            var id = dbServiceUser.saveUser(new User("Tom", 20 + i));

            Optional<User> user = dbServiceUser.getUser(id);

            user.ifPresentOrElse(
                    crUser -> logger.info("created user, name: {}", crUser.getName()),
                    () -> logger.info("user was not created")
            );
            System.out.println("================================");
        }
        for (long id = 1; id < 5; id++) {
            Optional<User> user = dbServiceUser.getUser(id);

            user.ifPresentOrElse(
                    crUser -> logger.info("created user, name: {}", crUser.getName()),
                    () -> logger.info("user was not created")
            );
            System.out.println("--------------------------------");
        }
        myCache.removeListener(listener);
    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}

package ru.package01;

import com.google.gson.Gson;
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
import ru.package01.mycache.MyCache;

import javax.sql.DataSource;
import java.util.Optional;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) throws InterruptedException {
        MyCache<String, User> myCache = new MyCache<>();

        myCache.addListener();
        Gson gson = new Gson();
        String json = gson.toJson(myCache);
        System.out.println("current state is:  " + json);

        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);
        var sessionManager = new SessionManagerJdbc(dataSource);

// Work with user
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();
        var entityClassMetaData = new EntityClassMetaDataImpl<User>(User.class);
        var entitySQLMetaData = new EntitySQLMetaDataImpl<User>(entityClassMetaData);
        var jdbcMapperUser = new JdbcMapperImpl<User>(sessionManager, dbExecutor, entitySQLMetaData, entityClassMetaData);

        UserDao userDao = new UserDaoJdbcMapper(jdbcMapperUser);
        var dbServiceUser = new DbServiceUserImpl(userDao, myCache);
        for (int i = 1; i <= 7; i++) {
            long id = dbServiceUser.saveUser(new User("Tom", 20 + i));
            Optional<User> user = dbServiceUser.getUser(id);

            user.ifPresentOrElse(
                    crUser -> logger.info("created user, name: {}", crUser.getName()),
                    () -> logger.info("user was not created")
            );
            System.out.println("================================");
        }

        json = gson.toJson(myCache);
        System.out.println("current state after FILLING is:  " + json);

        for (long id = 7; id > 1; id--) { //if read from 1 to 7 -> cache REwriting and there is no element already
            Optional<User> user = dbServiceUser.getUser(id);

            user.ifPresentOrElse(
                    crUser -> logger.info("created user, name: {}", crUser.getName()),
                    () -> logger.info("user was not created")
            );
            System.out.println("--------------------------------");
        }

        json = gson.toJson(myCache);
        System.out.println("current state before DELETE is:  " + json);

        myCache.removeListener();

        json = gson.toJson(myCache);
        System.out.println("current state after DELETE is:  " + json);
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

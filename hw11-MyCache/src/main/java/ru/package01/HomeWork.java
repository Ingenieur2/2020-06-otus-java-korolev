package ru.package01;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.Account;
import ru.package01.core.model.User;
import ru.package01.core.service.DBServiceAccountImpl;
import ru.package01.core.service.DbServiceUserImpl;
import ru.package01.h2.DataSourceH2;
import ru.package01.jdbc.DbExecutorImpl;
import ru.package01.jdbc.dao.AccountDaoJdbcMapper;
import ru.package01.jdbc.dao.UserDaoJdbcMapper;
import ru.package01.jdbc.mapper.EntityClassMetaDataImpl;
import ru.package01.jdbc.mapper.EntitySQLMetaDataImpl;
import ru.package01.jdbc.mapper.JdbcMapperImpl;
import ru.package01.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.util.Optional;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {

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
        var id = dbServiceUser.saveUser(new User( "Tom", 32));
        Optional<User> user = dbServiceUser.getUser(id);

        user.ifPresentOrElse(
                crUser -> logger.info("created user, name: {}", crUser.getName()),
                () -> logger.info("user was not created")
        );
        System.out.println("================================");

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

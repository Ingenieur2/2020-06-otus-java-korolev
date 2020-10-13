package ru.package01;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.AddressDataSet;
import ru.package01.core.model.PhoneDataSet;
import ru.package01.core.model.User;
import ru.package01.core.service.DBServiceUser;
import ru.package01.core.service.DbServiceUserImpl;
import ru.package01.flyway.MigrationsExecutor;
import ru.package01.flyway.MigrationsExecutorFlyway;
import ru.package01.hibernate.HibernateUtils;
import ru.package01.hibernate.dao.UserDaoHibernate;
import ru.package01.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.List;
import java.util.Optional;


public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        MigrationsExecutor migrationsExecutor = new MigrationsExecutorFlyway(HIBERNATE_CFG_FILE);
        migrationsExecutor.executeMigrations();

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        User user1 = new User();
        user1.setName("Tom");
        user1.setAge(5);
        AddressDataSet address1 = new AddressDataSet();
        address1.setStreet("Street 1");
        user1.setAddress(address1);

        PhoneDataSet phone1 = new PhoneDataSet();
        phone1.setNumber("11-11");
        phone1.setUser(user1);
        PhoneDataSet phone2 = new PhoneDataSet();
        phone2.setNumber("11-22");
        phone2.setUser(user1);
        user1.setPhone(List.of(phone1, phone2));

        long id = dbServiceUser.saveUser(user1);
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);
        outputUserOptional("Created user", mayBeCreatedUser);
//-------------------------------------------------------------------------------------------------
        User user2 = new User();
        user2.setName("Jerry"); //if "Tom" ->  Нарушение уникального индекса или первичного ключа: "PUBLIC.UK_3G1J96G94XPK3LPXL2QBL985X_INDEX_4 ON PUBLIC.USERS(NAME) VALUES 1"
        user2.setAge(10);
        AddressDataSet address2 = new AddressDataSet();
        address2.setStreet("Street 2");
        user2.setAddress(address2);

        PhoneDataSet phone3 = new PhoneDataSet();
        phone3.setNumber("11-55");
        phone3.setUser(user2);
        user2.setPhone(List.of(phone3));

        id = dbServiceUser.saveUser(user2);
        mayBeCreatedUser = dbServiceUser.getUser(id);
        outputUserOptional("Created user", mayBeCreatedUser);
//-------------------------------------------------------------------------------------------------
        user1.setName("DOG");
        user1.setAge(20);
        address1.setStreet("Street 0");
        user1.setAddress(address1);

        phone1.setNumber("23-45");
        phone1.setUser(user1);
        user1.setPhone(List.of(phone1, phone2)); // phone2 не поменялся

        id = dbServiceUser.saveUser(user1);
        mayBeCreatedUser = dbServiceUser.getUser(id);
        outputUserOptional("Created user", mayBeCreatedUser);
    }

    private static void outputUserOptional(String header, Optional<User> mayBeUser) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
    }
}

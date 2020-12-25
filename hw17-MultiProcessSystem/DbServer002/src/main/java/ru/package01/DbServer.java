package ru.package01;

import org.hibernate.SessionFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceUser;
import ru.package01.core.service.DbServiceUserImpl;
import ru.package01.flyway.MigrationsExecutor;
import ru.package01.flyway.MigrationsExecutorFlyway;
import ru.package01.hibernate.HibernateUtils;
import ru.package01.hibernate.repository.UserDaoHibernate;
import ru.package01.hibernate.sessionmanager.SessionManagerHibernate;
import com.google.gson.Gson;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class DbServer extends UnicastRemoteObject implements EchoInterface {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    private static final int SERVER_PORT = 8092;
    private static final int REGISTRY_PORT = 1101;

    private DbServer(int port) throws RemoteException {
        super(port);
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(REGISTRY_PORT);
            Naming.rebind("//localhost/DbServer002", new DbServer(SERVER_PORT));
            System.out.println("waiting for MessageServer connection 002...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String echo(String data) {


        MigrationsExecutor migrationsExecutor = new MigrationsExecutorFlyway(HIBERNATE_CFG_FILE);
        migrationsExecutor.executeMigrations();

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DbServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        Gson gson = new Gson();
        System.out.println(String.format("data from MessageServer 002 (client) (2): %s", data));
        User user = gson.fromJson(data, User.class);
        dbServiceUser.saveUser(user);
        String dataReply = gson.toJson(user);

        return dataReply;
    }
}


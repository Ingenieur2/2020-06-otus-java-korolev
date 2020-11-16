package ru.package01;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.hibernate.SessionFactory;
import ru.package01.core.dao.UserDao;
import ru.package01.core.model.AddressDataSet;
import ru.package01.core.model.PhoneDataSet;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceUser;
import ru.package01.core.service.DbServiceUserImpl;
import ru.package01.helpers.FileSystemHelper;
import ru.package01.hibernate.HibernateUtils;
import ru.package01.hibernate.dao.UserDaoHibernate;
import ru.package01.hibernate.sessionmanager.SessionManagerHibernate;
import ru.package01.server.UsersWebServer;
import ru.package01.server.UsersWebServerWithBasicSecurity;
import ru.package01.services.TemplateProcessor;
import ru.package01.services.TemplateProcessorImpl;

public class WebServerSimpleDemo {
    private static final int WEB_SERVER_PORT = 8082;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private static final String REALM_NAME = "AnyRealm";

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE,
                User.class, AddressDataSet.class, PhoneDataSet.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);

        UserDao userDao = new UserDaoHibernate(sessionManager);
        DbServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        String hashLoginServiceConfigPath = FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);
        LoginService loginService = new HashLoginService(REALM_NAME, hashLoginServiceConfigPath);

        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT, loginService, dbServiceUser,
                gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();

    }
}

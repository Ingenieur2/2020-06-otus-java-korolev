package ru.package01.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.package01.core.model.User;
import ru.package01.flyway.MigrationsExecutor;
import ru.package01.flyway.MigrationsExecutorFlyway;
import ru.package01.hibernate.HibernateUtils;

@Configuration
public class HibernateConfig {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    @Bean(initMethod = "executeMigrations")
    public MigrationsExecutor migrationsExecutor() {
        MigrationsExecutor migrationsExecutor = new MigrationsExecutorFlyway(HIBERNATE_CFG_FILE);
        return migrationsExecutor;
    }

    @Bean
    @DependsOn("migrationsExecutor")
    public SessionFactory sessionFactory() {
        return HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, User.class);
    }
}

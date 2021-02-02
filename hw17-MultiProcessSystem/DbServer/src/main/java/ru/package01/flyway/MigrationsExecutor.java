package ru.package01.flyway;

public interface MigrationsExecutor {
    void cleanDb();

    void executeMigrations();
}

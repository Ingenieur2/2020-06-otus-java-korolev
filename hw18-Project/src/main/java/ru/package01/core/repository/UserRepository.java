package ru.package01.core.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.package01.core.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select * from users where login = :login")
    List<User> findByLogin(@Param("login") String login);

    @Query("select * from users where password = :password")
    List<User> findByPassword(@Param("password") String password);
}

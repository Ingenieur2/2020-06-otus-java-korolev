package ru.package01.core.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.package01.core.model.Answer;


import java.util.LinkedList;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Query("select * from answers where login = :login")
    LinkedList<Answer> findByLogin(@Param("login") String login);

}

package ru.package01.core.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.package01.core.model.Question;
import ru.package01.core.model.User;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Query("select * from questions where question = :question")
    List<User> findByQuestion(@Param("question") String question);
}

package ru.package01.core.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.package01.core.model.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Query("select * from right_answers where question_id = :question_id")
    List<Answer> findByQuestionId(@Param("question_id") long question_id);
}

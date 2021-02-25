package ru.package01.core.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.package01.core.model.User_answer;

import java.util.List;

@Repository
public interface UserAnswerRepository extends CrudRepository<User_answer, Long> {

    @Query("select * from users where user_id = :user_id")
    List<User_answer> findByUserId(@Param("user_id") String user_id);
}

package ru.package01.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Table("answers")
public class Answer implements Serializable {
    @Id
    private long answer_id;
    private String login;
    private Object answer00;

    public Answer() {
    }

    @PersistenceConstructor
    public Answer(long answer_id, String login, Object answer00) {
        this.answer_id = answer_id;
        this.login = login;
        this.answer00 = answer00;
    }

    public long getId() {
        return answer_id;
    }

    public void setId(long answer_id) {
        this.answer_id = answer_id;
    }

    public String getLogin() {
        return login;
    }

    public Object getAnswer() {
        return answer00;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + answer_id +
                ", login='" + login + '\'' +
                ", answer='" + answer00 + '\'' +
                '}';
    }
}
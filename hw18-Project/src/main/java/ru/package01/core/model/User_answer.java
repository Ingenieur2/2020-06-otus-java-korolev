package ru.package01.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("user_answers")
public class User_answer {
    @Id
    @Column("id")
    private long id;
    @Column("user_id")
    private long user_id;
    @Column("question_id")
    private long question_id;
    @Column("answer_id")
    private long answer_id;
    @Column("checkbox")
    private boolean checkbox;

    public User_answer() {

    }

    @PersistenceConstructor
    public User_answer(long id, long user_id, long question_id, long answer_id, boolean checkbox) {
        this.id = id;
        this.user_id = user_id;
        this.question_id = question_id;
        this.answer_id = answer_id;
        this.checkbox = checkbox;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(long answer_id) {
        this.answer_id = answer_id;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    @Override
    public String toString() {
        return "User_answer{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", question_id=" + question_id +
                ", answer_id=" + answer_id +
                ", checkbox=" + checkbox +
                '}';
    }
}
package ru.package01.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("questions")
public class Question {
    @Id
    @Column("id")
    private long id;
    @Column("question")
    private String question;

    public Question() {
    }

    @PersistenceConstructor
    public Question(long id, String question) {
        this.id = id;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }

}
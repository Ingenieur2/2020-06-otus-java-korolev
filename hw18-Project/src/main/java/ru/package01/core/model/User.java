package ru.package01.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;


@Table("users")
public class User {
    @Id
    private long id;
    private String login;
    private String password;
    private Object answer;

    public User() {
    }

    @PersistenceConstructor
    public User(long id, String login, String password, Object answer) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Object getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
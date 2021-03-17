package ru.package01.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("right_answers")
public class Answer {
    @Id
    @Column("id")
    private long id;
    @Column("answer")
    private String answer;
    @Column("question_id")
    private long question_id;
    @Column("checkbox")
    private boolean checkbox;

    public Answer() {

    }

    @PersistenceConstructor
    public Answer(long id, String answer, long question_id, boolean checkbox) {
        this.id = id;
        this.answer = answer;
        this.question_id = question_id;
        this.checkbox = checkbox;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    @Override
    public String toString() {
        return "Right_Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", question_id=" + question_id +
                ", checkbox='" + checkbox + '\'' +
                '}';
    }
}

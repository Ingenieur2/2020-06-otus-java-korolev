package ru.package01.core.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table("questions")
public class Question {
    @Id
    private long question_id;
    private String theme;

    private String answer1;
    private boolean checkbox1;
    private String answer2;
    private boolean checkbox2;
    private String answer3;
    private boolean checkbox3;
    private String answer4;
    private boolean checkbox4;


    public Question() {
    }

    @PersistenceConstructor
    public Question(long question_id, String theme, String answer1, boolean checkbox1,
                    String answer2, boolean checkbox2,
                    String answer3, boolean checkbox3,
                    String answer4, boolean checkbox4) {
        this.question_id = question_id;
        this.theme = theme;
        this.answer1 = answer1;
        this.checkbox1 = checkbox1;
        this.answer2 = answer2;
        this.checkbox2 = checkbox2;
        this.answer3 = answer3;
        this.checkbox3 = checkbox3;
        this.answer4 = answer4;
        this.checkbox4 = checkbox4;

    }

    public long getQuestion_id() {
        return question_id;
    }

    public String getTheme() {
        return theme;
    }

    public String getAnswer1() {
        return answer1;
    }

    public boolean isCheckbox1() {
        return checkbox1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public boolean isCheckbox2() {
        return checkbox2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public boolean isCheckbox3() {
        return checkbox3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public boolean isCheckbox4() {
        return checkbox4;
    }


    public void setCheckbox3(boolean checkbox3) {
        this.checkbox3 = checkbox3;
    }

    public void setCheckbox4(boolean checkbox4) {
        this.checkbox4 = checkbox4;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", theme='" + theme + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", true/false1:'" + checkbox1 + '\'' +
                ", answer2='" + answer1 + '\'' +
                ", true/false2:'" + checkbox1 + '\'' +
                ", answer3='" + answer1 + '\'' +
                ", true/false3:'" + checkbox1 + '\'' +
                ", answer4='" + answer1 + '\'' +
                ", true/false4:'" + checkbox1 + '\'' +
                '}';
    }

}
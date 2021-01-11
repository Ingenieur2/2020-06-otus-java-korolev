package ru.package01.core.service;

import com.google.gson.Gson;
import ru.package01.core.model.Question;
import ru.package01.core.model.User;

import java.util.List;

public class CheckServiceImpl implements CheckService {

    private final DbServiceUser dbServiceUser;
    private final DbServiceQuestion dbServiceQuestion;

    public CheckServiceImpl(DbServiceUser dbServiceUser, DbServiceQuestion dbServiceQuestion) {
        this.dbServiceUser = dbServiceUser;
        this.dbServiceQuestion = dbServiceQuestion;
    }

    @Override
    public String[] checkAnswer(User user) {

        List<Question> questionsFromDB = dbServiceQuestion.getAll();
        Question[] questionsToArray = questionsFromDB.toArray(new Question[questionsFromDB.size()]);
        Object answer = (dbServiceUser.getUser(user.getId())).orElseThrow().getAnswer();

        Gson gson = new Gson();
        Question[] questionsFromUser = gson.fromJson(answer.toString(), Question[].class);
        int rightAnswers = 0;
        int changedQuestions = 0;
        for (int i = 0; i < questionsToArray.length; i++) {
            if ((questionsToArray[i].getQuestion_id() == questionsFromUser[i].getQuestion_id())
                    && (questionsToArray[i].getTheme().equals(questionsFromUser[i].getTheme()))
                    && (questionsToArray[i].getAnswer1().equals(questionsFromUser[i].getAnswer1()))
                    && (questionsToArray[i].getAnswer2().equals(questionsFromUser[i].getAnswer2()))
                    && (questionsToArray[i].getAnswer3().equals(questionsFromUser[i].getAnswer3()))
                    && (questionsToArray[i].getAnswer4().equals(questionsFromUser[i].getAnswer4()))
            ) {
                if ((questionsToArray[i].isCheckbox1() == questionsFromUser[i].isCheckbox1())
                        && (questionsToArray[i].isCheckbox2() == questionsFromUser[i].isCheckbox2())
                        && (questionsToArray[i].isCheckbox3() == questionsFromUser[i].isCheckbox3())
                        && (questionsToArray[i].isCheckbox4() == questionsFromUser[i].isCheckbox4())
                ) {
                    rightAnswers++;
                }
            } else {
                changedQuestions++;
            }
        }
        String[] resultString = new String[4];
        resultString[0] = String.valueOf(questionsToArray.length);
        resultString[1] = String.valueOf(questionsToArray.length - changedQuestions);
        resultString[2] = String.valueOf(rightAnswers);
        resultString[3] = String.valueOf((double) rightAnswers / questionsToArray.length);
        return resultString;
    }
}

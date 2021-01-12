package ru.package01.core.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.package01.core.model.Answer;
import ru.package01.core.model.Question;
import ru.package01.core.repository.AnswerRepository;

import java.util.List;

@Service
public class DbServiceAnswerAnswerImpl implements DbServiceAnswer {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceAnswerAnswerImpl.class);

    private final DbServiceQuestion dbServiceQuestion;
    private final AnswerRepository answerRepository;

    public DbServiceAnswerAnswerImpl(DbServiceQuestion dbServiceQuestion, AnswerRepository answerRepository) {
        this.dbServiceQuestion = dbServiceQuestion;
        this.answerRepository = answerRepository;
    }

    @Override
    public long saveAnswer(String answerString) {
        try {
            Gson gson = new Gson();
            Answer answer = gson.fromJson(answerString, Answer.class);

            answerRepository.save(answer);
            logger.info("created answer:_____" + answer.toString());

            long answerId = answerRepository.findByLogin(answer.getLogin()).getLast().getId();
            logger.info("created answer: {}", answerId);
            return answerId;
        } catch (Exception e) {
            System.out.println("DID NOT CREATED");
            throw new DbServiceException(e);
        }
    }

    @Override
    public String[] checkAnswer(String answerString) {
        List<Question> questionsFromDB = dbServiceQuestion.getAll();

        Gson gson = new Gson();
        Answer answer = gson.fromJson(answerString, Answer.class);
        Object user_answer = answer.getAnswer();
        Question[] questionsFromUser = gson.fromJson(user_answer.toString(), Question[].class);

        int rightAnswers = 0;
        int changedQuestions = 0;
        for (int i = 0; i < questionsFromDB.size(); i++) {
            if ((questionsFromDB.get(i).getQuestion_id() == questionsFromUser[i].getQuestion_id())
                    && (questionsFromDB.get(i).getTheme().equals(questionsFromUser[i].getTheme()))
                    && (questionsFromDB.get(i).getAnswer1().equals(questionsFromUser[i].getAnswer1()))
                    && (questionsFromDB.get(i).getAnswer2().equals(questionsFromUser[i].getAnswer2()))
                    && (questionsFromDB.get(i).getAnswer3().equals(questionsFromUser[i].getAnswer3()))
                    && (questionsFromDB.get(i).getAnswer4().equals(questionsFromUser[i].getAnswer4()))
            ) {
                if ((questionsFromDB.get(i).isCheckbox1() == questionsFromUser[i].isCheckbox1())
                        && (questionsFromDB.get(i).isCheckbox2() == questionsFromUser[i].isCheckbox2())
                        && (questionsFromDB.get(i).isCheckbox3() == questionsFromUser[i].isCheckbox3())
                        && (questionsFromDB.get(i).isCheckbox4() == questionsFromUser[i].isCheckbox4())
                ) {
                    rightAnswers++;
                }
            } else {
                changedQuestions++;
            }
        }
        String[] resultString = new String[4];
        resultString[0] = String.valueOf(questionsFromDB.size());
        resultString[1] = String.valueOf(questionsFromDB.size() - changedQuestions);
        resultString[2] = String.valueOf(rightAnswers);
        resultString[3] = String.valueOf((double) rightAnswers / questionsFromDB.size());
        return resultString;
    }
}

package ru.package01.core.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.package01.core.model.Answer;
import ru.package01.core.model.User_answer;
import ru.package01.core.repository.AnswerRepository;
import ru.package01.core.repository.UserAnswerRepository;

import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final UserAnswerRepository userAnswerRepository;
    private final AnswerRepository answerRepository;

    public CheckServiceImpl(UserAnswerRepository userAnswerRepository, AnswerRepository answerRepository) {
        this.userAnswerRepository = userAnswerRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public long saveUserAnswer(String userAnswerString) {
        try {
            Gson gson = new Gson();
            User_answer[] answersFromUser = gson.fromJson(userAnswerString, User_answer[].class);
            for (int i = 0; i < answersFromUser.length; i++) {
                userAnswerRepository.save(answersFromUser[i]);
                logger.info("created user answer:  " + answersFromUser[i]);
            }

            long userAnswerId = answersFromUser[0].getUser_id();
            logger.info("created answers for user: {}", userAnswerId);
            return userAnswerId;
        } catch (Exception e) {
            System.out.println("DID NOT CREATED");
            throw new DbServiceException(e);
        }
    }

    @Override
    public String[] checkAnswer(String userAnswerString) {

        List<Answer> answersFromDB = (List<Answer>) answerRepository.findAll();
        Gson gson = new Gson();
        User_answer[] answersFromUser = gson.fromJson(userAnswerString, User_answer[].class);

        long rightAnswers = 0;
        long maxQuestionId = 0;
        for (int i = 0; i < answersFromDB.size(); i++) {
            if (maxQuestionId < answersFromDB.get(i).getQuestion_id()) {
                maxQuestionId = answersFromDB.get(i).getQuestion_id();
            }
        }

        for (long i = 1; i <= maxQuestionId; i++) {
            long result = 1;
            for (int j = 0; j < answersFromDB.size(); j++) {
                if (i == (answersFromDB.get(j)).getQuestion_id()) {
                    if (answersFromDB.get(j).isCheckbox() != answersFromUser[j].isCheckbox()) {
                        result = 0;
                        j = answersFromDB.size() - 1;
                    }
                }
            }
            rightAnswers = rightAnswers + result;
        }

        String[] resultString = new String[3];
        resultString[0] = String.valueOf(maxQuestionId);
        resultString[1] = String.valueOf(rightAnswers);
        resultString[2] = String.valueOf((double) rightAnswers / maxQuestionId);
        return resultString;
    }
}

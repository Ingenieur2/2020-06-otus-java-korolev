package ru.package01.core.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.package01.core.model.Answer;
import ru.package01.core.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceAnswerImpl implements DbServiceAnswer {

    private static final Logger logger = LoggerFactory.getLogger(DbServiceAnswerImpl.class);

    private final AnswerRepository answerRepository;

    public DbServiceAnswerImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public long saveAnswer(String answerString) {
        try {

            long answerId = 0;
            Gson gson = new Gson();
            Answer answer = gson.fromJson(answerString, Answer.class);
            if ((answer.getQuestion_id() >= 0)
                    && (!answer.getAnswer().equals(""))
            ) {
                List<Answer> answersForThisQuestion = answerRepository.findByQuestionId(answer.getQuestion_id());

                for (int i = 0; i < answersForThisQuestion.size(); i++) {
                    if (answer.getAnswer().equals(answersForThisQuestion.get(i).getAnswer())) {
                        return answerId;
                    }
                }
                answerRepository.save(answer);
                logger.info("created answer:_____");
            }

            answerId = answer.getId();
            logger.info("created answer: {}", answerId);
            return answerId;
        } catch (Exception e) {
            System.out.println("DID NOT CREATED");
            throw new DbServiceException(e);
        }
    }

    @Override
    public Optional<Answer> getAnswer(long id) {
        try {
            Optional<Answer> answerOptional = answerRepository.findById(id);
            logger.info("answer: {}", answerOptional.orElse(null));
            return answerOptional;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Answer> getAnswersForThisQuestion(long question_id) {
        return (List<Answer>) answerRepository.findByQuestionId(question_id);
    }

    @Override
    public List<Answer> getAll() {
        return (List<Answer>) answerRepository.findAll();
    }
}


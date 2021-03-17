package ru.package01.core.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.package01.core.model.Question;
import ru.package01.core.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceQuestionImpl implements DbServiceQuestion {

    private static final Logger logger = LoggerFactory.getLogger(DbServiceQuestionImpl.class);

    private final QuestionRepository questionRepository;

    public DbServiceQuestionImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public long saveQuestion(String questionString) {
        try {
            Gson gson = new Gson();
            Question question = gson.fromJson(questionString, Question.class);
            if ((questionRepository.findByQuestion(question.getQuestion()).isEmpty())
                    && !question.getQuestion().equals("")
            ) {
                questionRepository.save(question);
                logger.info("created question:_____");
            }

            long questionId = question.getId();
            logger.info("created question: {}", questionId);
            return questionId;
        } catch (Exception e) {
            System.out.println("DID NOT CREATED");
            throw new DbServiceException(e);
        }
    }

    @Override
    public Optional<Question> getQuestion(long id) {
        try {
            Optional<Question> questionOptional = questionRepository.findById(id);
            logger.info("question: {}", questionOptional.orElse(null));
            return questionOptional;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Question> getAll() {
        return (List<Question>) questionRepository.findAll();
    }
}

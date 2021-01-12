package ru.package01.core.service;

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
    public long saveQuestion(Question question) {
        try {
            if (question.getAnswer3().equals("")) {
                question.setCheckbox3(false);
            }
            if (question.getAnswer4().equals("")) {
                question.setCheckbox4(false);
            }
            if (((questionRepository.findByThemeOfQuestion(question.getTheme()).isEmpty())
                    && !question.getTheme().equals(""))
                    && !question.getAnswer1().equals("")
                    && !question.getAnswer2().equals("")
                    && (question.isCheckbox1() || question.isCheckbox2() || question.isCheckbox3() || question.isCheckbox4())

            ) {
                logger.info("created question:_____");
                questionRepository.save(question);
            }

            long questionId = question.getQuestion_id();
            logger.info("created question: {}", questionId);
            return questionId;
        } catch (Exception e) {
            System.out.println("--DID NOT CREATED--");
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

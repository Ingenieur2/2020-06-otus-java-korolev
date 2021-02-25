package ru.package01.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.package01.core.model.Question;
import ru.package01.core.service.DbServiceQuestion;

import java.util.List;
import java.util.Optional;

@Controller
public class QuestionController {
    private final SimpMessagingTemplate messagingTemplate;
    private final DbServiceQuestion dbServiceQuestion;


    public QuestionController(SimpMessagingTemplate messagingTemplate, DbServiceQuestion dbServiceQuestion) {
        this.messagingTemplate = messagingTemplate;
        this.dbServiceQuestion = dbServiceQuestion;
    }

    @MessageMapping("/chat.addQuestion")
    public void questionSave(String questionString) {
        long id = dbServiceQuestion.saveQuestion(questionString);
        if (id != 0) {
            messagingTemplate.convertAndSend("/topic/questions", dbServiceQuestion.getQuestion(id));
        }
    }

    @MessageMapping("/chat.getAllQuestions")
    public void getAll() {
        List<Question> questions = dbServiceQuestion.getAll();
        messagingTemplate.convertAndSend("/topic/getAllQuestions", questions);
    }

    @MessageMapping("/chat.getQuestion")
    public void getQuestion(String id) {
        Optional<Question> question = dbServiceQuestion.getQuestion(Long.parseLong(id));
        messagingTemplate.convertAndSend("/topic/getQuestion", question.orElseThrow());
    }
}

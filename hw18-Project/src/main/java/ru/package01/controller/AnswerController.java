package ru.package01.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.package01.core.model.Answer;
import ru.package01.core.service.DbServiceAnswer;


@Controller
public class AnswerController {
    private final SimpMessagingTemplate messagingTemplate;
    private final DbServiceAnswer dbServiceAnswer;


    public AnswerController(SimpMessagingTemplate messagingTemplate, DbServiceAnswer dbServiceAnswer) {
        this.messagingTemplate = messagingTemplate;
        this.dbServiceAnswer = dbServiceAnswer;
    }


    @MessageMapping("/chat.addUserAnswer")
    public void answerSave(String answerString) {
        dbServiceAnswer.saveAnswer(answerString);
        messagingTemplate.convertAndSend("/topic/usersResult", dbServiceAnswer.checkAnswer(answerString));
    }
}

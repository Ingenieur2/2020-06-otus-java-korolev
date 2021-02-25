package ru.package01.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.package01.core.service.CheckService;


@Controller
public class UserAnswerController {
    private final SimpMessagingTemplate messagingTemplate;
    private final CheckService checkService;


    public UserAnswerController(SimpMessagingTemplate messagingTemplate, CheckService checkService) {
        this.messagingTemplate = messagingTemplate;
        this.checkService = checkService;
    }

    @MessageMapping("/chat.checkUserAnswer")
    public void userAnswerCheck(String userAnswerString) {
        String[] result = checkService.checkAnswer(userAnswerString);
        messagingTemplate.convertAndSend("/topic/usersResult", result);
    }

    @MessageMapping("/chat.addUserAnswer")
    public void userAnswerSave(String userAnswerString) {
        checkService.saveUserAnswer(userAnswerString);
    }
}
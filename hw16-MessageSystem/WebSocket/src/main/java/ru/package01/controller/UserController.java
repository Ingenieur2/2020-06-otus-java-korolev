package ru.package01.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.package01.core.model.User;
import ru.package01.services.FrontendService;

@Controller
public class UserController {
    private final FrontendService frontendService;
    private final SimpMessagingTemplate messagingTemplate;


    public UserController(FrontendService frontendService, SimpMessagingTemplate messagingTemplate) {
        this.frontendService = frontendService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.addUser")
    public void userSave(User user) {
        frontendService.saveUser(user, callbackUser -> messagingTemplate.convertAndSend("/topic/users",
                callbackUser));
    }
}

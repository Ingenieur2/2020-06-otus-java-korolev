package ru.package01.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceQuestion;
import ru.package01.core.service.DbServiceUser;

import java.util.List;

@Controller
public class UserController {
    private final SimpMessagingTemplate messagingTemplate;
    private final DbServiceUser dbServiceUser;
    private final DbServiceQuestion dbServiceQuestion;


    public UserController(SimpMessagingTemplate messagingTemplate, DbServiceUser dbServiceUser, DbServiceQuestion dbServiceQuestion) {
        this.messagingTemplate = messagingTemplate;
        this.dbServiceUser = dbServiceUser;
        this.dbServiceQuestion = dbServiceQuestion;
    }

    @MessageMapping("/chat.addUser")
    public void userSave(User user) {
        long id = dbServiceUser.saveUser(user);
        if (id != 0) {
            messagingTemplate.convertAndSend("/topic/users", dbServiceUser.getUser(id));
        }
    }

    @MessageMapping("/chat.checkUser")
    public void userCheck(User user) {
        long id = dbServiceUser.checkUser(user);
        messagingTemplate.convertAndSend("/topic/users", dbServiceUser.getUser(id));
    }

    @MessageMapping("/chat.getAll")
    public void getAll() {
        List<User> users = dbServiceUser.getAll();
        messagingTemplate.convertAndSend("/topic/getAll", users);
    }
}

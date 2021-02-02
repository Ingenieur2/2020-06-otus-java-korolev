package ru.package01.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.package01.EchoInterface;
import ru.package01.core.model.User;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


@Controller
public class UserController {
    private final SimpMessagingTemplate messagingTemplate;


    public UserController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.addUser")
    public void userSave(@RequestBody User user) throws RemoteException, NotBoundException, MalformedURLException {
        Gson gson = new Gson();
        String data = gson.toJson(user);

        EchoInterface echoInterface = (EchoInterface) Naming.lookup("//localhost/MessageServer");
        var dataFromServer = echoInterface.echo(data);
        System.out.println(String.format("response from the MessageServer (1) : %s", dataFromServer));
        User userBack = gson.fromJson(dataFromServer, User.class);

        messagingTemplate.convertAndSend("/topic/users", userBack);
    }
}

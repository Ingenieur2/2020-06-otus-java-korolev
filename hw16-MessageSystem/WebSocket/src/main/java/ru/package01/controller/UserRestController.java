package ru.package01.controller;

import org.springframework.web.bind.annotation.*;
import ru.package01.MessageAppl;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceUser;

import java.util.List;


@RestController
public class UserRestController {
    private final DbServiceUser dbServiceUser;

    public UserRestController(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping("/api/user")
    public List<User> getUsers() {
        return dbServiceUser.getAll();
    }

    @PostMapping("/api/user")
    public User userSave(@RequestBody User user) throws InterruptedException {

        dbServiceUser.saveUser(user);
        MessageAppl messageAppl = new MessageAppl(user);
        return user;
    }
}


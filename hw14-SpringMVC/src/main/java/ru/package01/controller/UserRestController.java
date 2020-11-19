package ru.package01.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
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
    public List getUsers() {
        return dbServiceUser.getAll();
    }

    @PostMapping("/api/user")
    public RedirectView userSave(@ModelAttribute User user) {
        dbServiceUser.saveUser(user);
        return new RedirectView("/", true);
    }
}

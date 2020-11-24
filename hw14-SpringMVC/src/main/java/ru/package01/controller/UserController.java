package ru.package01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceUser;

import java.util.List;

@Controller
public class UserController {
    private final DbServiceUser dbServiceUser;

    public UserController(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping({"/"})
    public String startView() {
        return "index.html";
    }

    @GetMapping({"/users"})
    public String userListView(Model model) {
        List<User> users = dbServiceUser.getAll();
        model.addAttribute("users", users);
        return "users.html";
    }

    @PostMapping("/users")
    public RedirectView userSave(@ModelAttribute User user) {
        dbServiceUser.saveUser(user);
        return new RedirectView("/", true);
    }
}
package com.example.springwebsecurityc51.controller;

import com.example.springwebsecurityc51.entity.User;
import com.example.springwebsecurityc51.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(User user) {
        userService.save(user);
        return "reg";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

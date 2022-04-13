package com.example.springwebsecurityc51.controller;

import com.example.springwebsecurityc51.dto.UserDTO;
import com.example.springwebsecurityc51.entity.User;
import com.example.springwebsecurityc51.repository.UserRepository;
import com.example.springwebsecurityc51.service.UserService;
import com.example.springwebsecurityc51.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String MSG_USER_EXITS = "user exists";
    private static final String MSG_USER_LOGIN_INVALID = "invalid user/login";
    public static final String PATH_USERS = "user/users";
    public static final String PATH_USER = "user/user";
    public static final String PATH_USER_REGISTRATION = "user/reg";
    public static final String PATH_USER_LOGIN = "user/login";
    public static final String PATH_INDEX = "user/index";

    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model, HttpSession session) {
        model.addAttribute("users", userService.findAll());

        return PATH_USERS;
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
        Optional<User> optionalUser = userService.findById(id);
        User user = optionalUser.orElse(null);
        model.addAttribute("user", user);

        return PATH_USER;
    }

    @GetMapping("/reg")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/reg";
    }

    @PostMapping("/reg")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return PATH_USER_REGISTRATION;
        }

        if (userService.findByUsername(user.getName()).isPresent()) {
            model.addAttribute("msgerror", MSG_USER_EXITS);

            return PATH_USER_REGISTRATION;
        }
        userService.save(user);

        return PATH_USER_LOGIN;
    }

    @GetMapping("/login")
    public String showLoginPage(@ModelAttribute("user") User user) {
        return PATH_USER_LOGIN;
    }

    @PostMapping("user/login")
    public String login(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return PATH_USER_LOGIN;
        } else if (userValidator.isValid(userDTO)) {

        } else {
            model.addAttribute("msgerror", MSG_USER_LOGIN_INVALID);
            return PATH_USER_LOGIN;
        }

        return PATH_INDEX;
    }
}

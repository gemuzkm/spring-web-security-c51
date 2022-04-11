package com.example.springwebsecurityc51.controller;

import com.example.springwebsecurityc51.entity.User;
import com.example.springbootc51.dto.UserDTO;
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String showAllUsers(Model model, HttpSession session) {
        model.addAttribute("users", userRepository.findAll());

        return "user/users";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);
        model.addAttribute("user", user);

        return "user/user";
    }

    @GetMapping("/reg")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/reg";
    }

    @PostMapping("/reg")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/reg";
        }

        if (userRepository.findByName(user.getName()).isPresent()) {
            model.addAttribute("msgerror", MSG_USER_EXITS);

            return "user/reg";
        }

        userService.save(user);

        return "user/login";
    }

    @GetMapping("/login")
    public String showLoginPage(@ModelAttribute("user") User user) {
        return "user/login";
    }

    @PostMapping("user/login")
    public String login(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/login";
        } else if (userValidator.isValid(userDTO)) {

        } else {
            model.addAttribute("msgerror", MSG_USER_LOGIN_INVALID);
            return "user/login";
        }

        return "user/index";
    }
}

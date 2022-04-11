package com.example.springwebsecurityc51.validator;

import com.example.springwebsecurityc51.dto.UserDTO;
import com.example.springwebsecurityc51.entity.User;
import com.example.springwebsecurityc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public boolean isValid(UserDTO userDTO) {
        return isValidUserName(userDTO) & isValidUserPassword(userDTO);
    }

    private boolean isValidUserName(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getName()).isPresent();
    }

    private boolean isValidUserPassword(UserDTO userDTO) {
        Optional<User> byUsername = userRepository.findByUsername(userDTO.getName());
        User user = byUsername.get();
        return user.getPassword().equals(userDTO.getPassword());
    }
}

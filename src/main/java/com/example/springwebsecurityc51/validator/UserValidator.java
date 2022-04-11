package com.example.springbootc51.validator;

import com.example.springbootc51.dto.UserDTO;
import com.example.springbootc51.entity.User;
import com.example.springbootc51.repository.UserRepository;
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
        return userRepository.findByName(userDTO.getName()).isPresent();
    }

    private boolean isValidUserPassword(UserDTO userDTO) {
        Optional<User> byUsername = userRepository.findByName(userDTO.getName());
        User user = byUsername.get();
        return user.getPassword().equals(userDTO.getPassword());
    }
}

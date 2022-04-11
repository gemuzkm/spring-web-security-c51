package com.example.springwebsecurityc51.service;

import com.example.springwebsecurityc51.entity.Operation;
import com.example.springwebsecurityc51.entity.User;
import com.example.springwebsecurityc51.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class OperationService {

    private final UserRepository userRepository;

    private final UserService userService;

    public OperationService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void save(Operation operation) {
        User user = userService.getCurrentUser();
        List<Operation> operationList = user.getOperationList();
        operationList.add(operation);
        user.setOperationList(operationList);
        userRepository.save(user);
    }
}

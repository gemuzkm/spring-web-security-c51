package com.example.springwebsecurityc51.service;

import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import com.example.springbootc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class OperationService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user, Operation operation) {
        List<Operation> operationList = user.getOperationList();
        operationList.add(operation);
        user.setOperationList(operationList);
        userRepository.save(user);
    }
}

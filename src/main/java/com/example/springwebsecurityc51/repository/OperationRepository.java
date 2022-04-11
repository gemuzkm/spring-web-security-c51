package com.example.springwebsecurityc51.repository;

import com.example.springwebsecurityc51.entity.Operation;
import com.example.springwebsecurityc51.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByUser(User user);
}

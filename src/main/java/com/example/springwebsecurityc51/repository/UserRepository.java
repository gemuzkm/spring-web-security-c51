package com.example.springwebsecurityc51.repository;

import com.example.springwebsecurityc51.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByName(String name);
}

package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);

    void update(User user);

    void delete(User user);

    User findById(Long id);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    User registerUser(String username, String password, String email, String role);
}
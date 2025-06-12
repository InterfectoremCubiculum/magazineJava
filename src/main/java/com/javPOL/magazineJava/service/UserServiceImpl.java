package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.UserDAO.UserDao;
import com.javPOL.magazineJava.enums.Role;
import com.javPOL.magazineJava.model.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j; // Automatyczne tworzenie loggera
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Attempting to load user by username: {}", username);
        return userDao.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return new UsernameNotFoundException("User not found: " + username);
                });
    }

    @Override
    public void save(User user) {
        log.info("Saving user with username: {}", user.getUsername());
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            log.debug("Encoding password for user: {}", user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.save(user);
        log.info("User saved successfully with ID: {}", user.getId());
    }

    @Override
    public void update(User user) {
        log.info("Updating user with ID: {}", user.getId());
        userDao.update(user);
        log.info("User updated successfully with ID: {}", user.getId());
    }

    @Override
    public void delete(User user) {
        log.warn("Deleting user with ID: {}", user.getId());
        userDao.delete(user);
        log.warn("User deleted successfully with ID: {}", user.getId());
    }

    @Override
    public User findById(Long id) {
        log.debug("Fetching user with ID: {}", id);
        return userDao.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new EntityNotFoundException("User not found with id: " + id);
                });
    }

    @Override
    public List<User> findAll() {
        log.info("Fetching all users");
        List<User> users = userDao.findAll();
        log.info("Total users fetched: {}", users.size());
        return users;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.debug("Searching for user by username: {}", username);
        Optional<User> user = userDao.findByUsername(username);
        if (user.isPresent()) {
            log.info("User found with username: {}", username);
        } else {
            log.warn("No user found with username: {}", username);
        }
        return user;
    }

    @Override
    public User registerUser(String username, String password, String email, String role) {
        log.info("Registering new user with username: {}, email: {}, and role: {}", username, email, role);
        User user = new User();
        user.setUsername(username);
        log.debug("Encoding password for new user: {}", username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(Role.valueOf(role.toUpperCase()));
        user.setEnabled(true);

        userDao.save(user);
        log.info("User registered successfully with ID: {}", user.getId());
        return user;
    }
}
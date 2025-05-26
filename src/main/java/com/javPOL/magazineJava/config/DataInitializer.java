package com.javPOL.magazineJava.config;

import com.javPOL.magazineJava.model.User;
import com.javPOL.magazineJava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findByUsername("admin").isEmpty()) {
            userService.registerUser("admin", "admin123", "admin@example.com", "ADMIN");
            System.out.println("Created admin user: admin/admin123");
        }

        if (userService.findByUsername("user").isEmpty()) {
            userService.registerUser("user", "user123", "user@example.com", "USER");
            System.out.println("Created test user: user/user123");
        }
    }
}
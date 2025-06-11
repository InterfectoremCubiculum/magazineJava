package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.dto.LoginRequest;
import com.javPOL.magazineJava.dto.RegisterRequest;
import com.javPOL.magazineJava.model.User;
import com.javPOL.magazineJava.security.JwtUtil;
import com.javPOL.magazineJava.service.EmailServiceImpl;
import com.javPOL.magazineJava.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final EmailServiceImpl emailServiceImpl;

    public AuthController(UserService userService,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            EmailServiceImpl emailServiceImpl) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.emailServiceImpl = emailServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            User user = (User) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("userId", user.getId());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid credentials");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            if (userService.findByUsername(registerRequest.getUsername()).isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Username already exists");
                return ResponseEntity.badRequest().body(error);
            }

            User user = userService.registerUser(
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getEmail(),
                    registerRequest.getRole());

            String token = jwtUtil.generateToken(user);

            // Po rejestracji wysyłamy powiadomienie email
            String subject = "Welcome to our magazine, " + user.getUsername() + "!";
            String text = "<h1>Welcome, " + user.getUsername() + "!</h1>"
                    + "<p>Thank you for registering in our system.</p>"
                    + "<p>You can now log in using your account.</p>";

            emailServiceImpl.sendSimpleMessage(user.getEmail(), subject, text);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("userId", user.getId());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getName().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            Map<String, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("userId", user.getId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Not authenticated");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = jwtUtil.extractUsername(token);
                User user = (User) userService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, user)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("valid", true);
                    response.put("username", user.getUsername());
                    response.put("role", user.getRole());
                    response.put("userId", user.getId());
                    return ResponseEntity.ok(response);
                }
            }
            return ResponseEntity.status(401).body(Map.of("valid", false, "error", "Invalid token"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("valid", false, "error", "Token validation failed"));
        }
    }
}
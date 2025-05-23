package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @GetMapping
    public ResponseEntity<?> getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(401).body("Authentication required");
        }

        User user = (User) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cart for user: " + user.getUsername());
        response.put("userId", user.getId());
        response.put("customerId", user.getCustomer() != null ? user.getCustomer().getId() : null);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(401).body("Authentication required");
        }

        User user = (User) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product added to cart for user: " + user.getUsername());
        response.put("userId", user.getId());
        response.put("productId", request.get("productId"));
        response.put("quantity", request.get("quantity"));

        return ResponseEntity.ok(response);
    }
}
package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.dto.CreateOrderRequestDto;
import com.javPOL.magazineJava.model.Order;
import com.javPOL.magazineJava.model.User;
import com.javPOL.magazineJava.service.OrderService;
import com.javPOL.magazineJava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @GetMapping("/my-orders")
    public List<Order> getMyOrders(@AuthenticationPrincipal User currentUser) {
        return orderService.findAllByUser(currentUser);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
        return orderService.findAllByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return orderService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOrderRequestDto requestDto) {
        orderService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public void update(@RequestBody Order order) {
        orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Order order = orderService.findById(id);
        if (order != null) {
            orderService.delete(order);
        }
    }
}
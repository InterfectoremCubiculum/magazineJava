package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.Order;
import com.javPOL.magazineJava.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return orderService.findById(id);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Order order) {
        orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody Order order) {
        orderService.update(order);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Order order = orderService.findById(id);
        if (order != null) {
            orderService.delete(order);
        }
    }
}
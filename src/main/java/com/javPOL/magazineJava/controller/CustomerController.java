package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.Customer;
import com.javPOL.magazineJava.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        return customerService.findById(id);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Customer customer) {
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody Customer customer) {
        customerService.update(customer);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            customerService.delete(customer);
        }
    }
}
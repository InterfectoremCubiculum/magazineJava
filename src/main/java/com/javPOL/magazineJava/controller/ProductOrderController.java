package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.ProductOrder;
import com.javPOL.magazineJava.service.ProductOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-orders")
public class ProductOrderController {
    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping
    public List<ProductOrder> getAll() {
        return productOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ProductOrder getById(@PathVariable int id) {
        return productOrderService.findById(id);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductOrder productOrder) {
        productOrderService.save(productOrder);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody ProductOrder productOrder) {
        productOrderService.update(productOrder);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        ProductOrder productOrder = productOrderService.findById(id);
        if (productOrder != null) {
            productOrderService.delete(productOrder);
        }
    }
}
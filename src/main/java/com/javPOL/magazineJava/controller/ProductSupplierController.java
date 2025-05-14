package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.service.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-suppliers")
public class ProductSupplierController {

    private final ProductSupplierService service;

    @Autowired
    public ProductSupplierController(ProductSupplierService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductSupplier> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSupplier> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductSupplier create(@RequestBody ProductSupplier ps) {
        return service.create(ps);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSupplier> update(@PathVariable int id, @RequestBody ProductSupplier ps) {
        try {
            return ResponseEntity.ok(service.update(id, ps));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


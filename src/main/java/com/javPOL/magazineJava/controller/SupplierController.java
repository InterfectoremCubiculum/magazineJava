package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.Supplier;
import com.javPOL.magazineJava.service.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Transactional
    @PostMapping
    public void create(@RequestBody Supplier supplier) {
        supplierService.save(supplier);
    }
    @Transactional
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        supplierService.update(supplier);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            supplierService.delete(supplier);
        }
    }

    @GetMapping("/{id}")
    public Supplier get(@PathVariable Long id) {
        return supplierService.findById(id);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.findAll();
    }
}

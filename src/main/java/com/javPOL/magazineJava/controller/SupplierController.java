package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.Supplier;
import com.javPOL.magazineJava.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public void save(@RequestBody Supplier supplier) {
        supplierService.save(supplier);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        supplierService.update(supplier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            supplierService.delete(supplier);
        }
    }

    @GetMapping("/{id}")
    public Supplier get(@PathVariable int id) {
        return supplierService.findById(id);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.findAll();
    }
}

package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.model.ProductSupplierId;
import com.javPOL.magazineJava.service.ProductSupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-suppliers")
public class ProductSupplierController {

    @Autowired
    private ProductSupplierService productSupplierService;

    @GetMapping
    public List<ProductSupplier> getAll() {
        return productSupplierService.findAll();
    }

    @GetMapping("/{productId}/{supplierId}")
    public ProductSupplier getById(@PathVariable Long productId, @PathVariable Long supplierId) {
        ProductSupplierId id = new ProductSupplierId(productId, supplierId);
        return productSupplierService.findById(id);
    }

    @Transactional
    @PostMapping
    public void create(@RequestBody ProductSupplier productSupplier) {
        productSupplierService.save(productSupplier);
    }
    @Transactional
    @PutMapping
    public void update(@RequestBody ProductSupplier productSupplier) {
        productSupplierService.update(productSupplier);
    }
    @Transactional
    @DeleteMapping("/{productId}/{supplierId}")
    public void delete(@PathVariable Long productId, @PathVariable Long supplierId) {
        ProductSupplierId id = new ProductSupplierId(productId, supplierId);
        ProductSupplier ps = productSupplierService.findById(id);
        if (ps != null) {
            productSupplierService.delete(ps);
        }
    }
}

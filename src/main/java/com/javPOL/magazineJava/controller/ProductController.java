package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }
    @GetMapping(params = { "page", "size" })
    public Page<Product> getAllPaged(@PageableDefault() Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping(params = { "page", "size", "categoryId"})
    public Page<Product> getAllPaged(
            @PageableDefault() Pageable pageable,
            @RequestParam Long categoryId) {
        return productService.findAll(pageable, categoryId);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProductDto productDto) {
        Product product = productService.create(productDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productService.update(id, productDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(product);
        return ResponseEntity.noContent().build();
    }
}

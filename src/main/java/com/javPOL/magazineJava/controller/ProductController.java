package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        log.info("Get all products");
        var list = productService.findAll();

        log.info("Products found. Quantity: {}", list.size());
        return list;
    }
    @GetMapping(params = { "page", "size" })
    public Page<Product> getAllPaged(@PageableDefault() Pageable pageable) {
        log.info("Get all products pageable");
        var page = productService.findAll(pageable);

        log.info("Products found. Total quantity: {}, Page: {}", page.getTotalElements(), page.getTotalPages());
        return page;
    }

    @GetMapping(params = { "page", "size", "categoryId"})
    public Page<Product> getAllPaged( @PageableDefault() Pageable pageable, @RequestParam Long categoryId) {
        log.info("Get all products pageable with category filltr");
        var page =  productService.findAll(pageable, categoryId);

        log.info("Products found. Total quantity: {}, Page: {}", page.getTotalElements(), page.getTotalPages());
        return page;
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        log.info("Get product with id: {}", id);
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProductDto productDto) {
        log.info("Create product: {}", productDto);
        Product product = productService.create(productDto);

        log.info("Product created: {}", product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        log.info("Update product: {}", productDto);
        productService.update(id, productDto);

        log.info("Product updated: {}", productDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete product: {}", id);
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(product);
        
        log.info("Product deleted: {}", product);
        return ResponseEntity.noContent().build();
    }
}

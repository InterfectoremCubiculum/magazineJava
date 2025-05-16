package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.Category;
import com.javPOL.magazineJava.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        return categoryService.findById(id);
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Transactional
    @PutMapping
    public void update(@RequestBody Category category) {
        categoryService.update(category);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            categoryService.delete(category);
        }
    }
}

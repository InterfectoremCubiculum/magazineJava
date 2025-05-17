package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    void update(Category category);
    void delete(Category category);
    Category findById(Long id);
    List<Category> findAll();
}

package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CategoryDAO.CategoryDao;
import com.javPOL.magazineJava.model.Category;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // Automatycznie tworzy statyczny logger
@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void save(Category category) {
        log.info("Saving category: {}", category.getName());
        categoryDao.save(category);
        log.info("Category saved successfully: {}", category.getName());
    }

    @Override
    public void update(Category category) {
        log.info("Updating category with id: {}", category.getId());
        categoryDao.update(category);
        log.info("Category updated successfully with id: {}", category.getId());
    }

    @Override
    public void delete(Category category) {
        log.warn("Deleting category with id: {}", category.getId());
        categoryDao.delete(category);
        log.warn("Category deleted successfully with id: {}", category.getId());
    }

    @Override
    public Category findById(Long id) {
        log.debug("Fetching category with id: {}", id);
        return categoryDao.findById(id)
                .orElseThrow(() -> {
                    log.error("Category not found with id: {}", id);
                    return new EntityNotFoundException("Category not found with id: " + id);
                });
    }

    @Override
    public List<Category> findAll() {
        log.debug("Fetching all categories.");
        List<Category> categories = categoryDao.findAll();
        log.debug("Total categories fetched: {}", categories.size());
        return categories;
    }
}
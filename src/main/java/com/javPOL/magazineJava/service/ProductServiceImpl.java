package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CategoryDAO.CategoryDao;
import com.javPOL.magazineJava.dao.ProductDAO.ProductDao;
import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Category;
import com.javPOL.magazineJava.model.Product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // Automatycznie tworzy statyczny logger
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;

    public ProductServiceImpl(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Transactional
    @Override
    public Product create(ProductDto productDto) {
        log.info("Creating product with name {}", productDto.getName());
        Category category = categoryDao.findById(productDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + productDto.getCategoryId()));

        Product product = new Product(
                null,
                category,
                productDto.getName(),
                productDto.getWeight(),
                productDto.getPrice(),
                productDto.getDescription()
        );
        productDao.save(product);
        return product;
    }

    @Transactional
    @Override
    public void update(Long id, ProductDto productDto) {
        log.info("Updating product with id {}", id);
        Category category = categoryDao.findById(productDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + productDto.getCategoryId()));

        Product product = new Product(
                id,
                category,
                productDto.getName(),
                productDto.getWeight(),
                productDto.getPrice(),
                productDto.getDescription()
        );
        productDao.update(product);
    }

    @Transactional
    @Override
    public void delete(Product product) {
        log.info("Deleting product with id {}", product.getId());
        productDao.delete(product);
    }

    @Override
    public Product findById(Long id) {
        log.info("Finding product with id {}", id);
        return productDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> findAll() {
        log.info("Finding all products");
        return productDao.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        log.info("Finding all products with pageable {}", pageable);
        return productDao.findAll(pageable);
    }
    @Override
    public Page<Product> findAll(Pageable pageable, Long categoryId) {
        log.info("Finding all products with pageable {}and categoryId {}", pageable, categoryId);
        return productDao.findAll(pageable, categoryId);
    }

}

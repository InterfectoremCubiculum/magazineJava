package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CategoryDAO.CategoryDao;
import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Category;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.repository.ProductRepository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    @Override
    public Product create(ProductDto productDto) {

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
        productRepository.save(product);
        return product;
    }

    @Transactional
    @Override
    public void update(Long id, ProductDto productDto) {
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
        productRepository.update(product);
    }

    @Transactional
    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    @Override
    public Page<Product> findAll(Pageable pageable, Long categoryId) {
        return productRepository.findAll(pageable, categoryId);
    }

}

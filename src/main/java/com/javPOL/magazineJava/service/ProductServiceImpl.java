package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CategoryDAO.CategoryDao;
import com.javPOL.magazineJava.dao.ProductDAO.ProductDao;
import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Category;
import com.javPOL.magazineJava.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    @Override
    public Product create(ProductDto productDto) {

        Category category = categoryDao.findById(productDto.getCategoryId());
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
        Category category = categoryDao.findById(productDto.getCategoryId());
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
        productDao.delete(product);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

}

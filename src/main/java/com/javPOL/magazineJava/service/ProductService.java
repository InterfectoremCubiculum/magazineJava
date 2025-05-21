package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    Product create(ProductDto product);
    void update(Long id, ProductDto product);
    void delete(Product product);
    Product findById(Long id);
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
}

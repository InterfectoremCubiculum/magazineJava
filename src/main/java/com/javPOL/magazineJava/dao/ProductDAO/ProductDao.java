package com.javPOL.magazineJava.dao.ProductDAO;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDao extends Dao<Product, Long> {
    Page<Product> findAll(Pageable pageable, Long categoryId);
}

package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.ProductOrder;

import java.util.List;

public interface ProductOrderService {
    void save(ProductOrder productOrder);

    void update(ProductOrder productOrder);

    void delete(ProductOrder productOrder);

    ProductOrder findById(int id);

    List<ProductOrder> findAll();
}

package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    void update(Order order);

    void delete(Order order);

    Order findById(int id);

    List<Order> findAll();
}
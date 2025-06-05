package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dto.CreateOrderRequestDto;
import com.javPOL.magazineJava.model.Order;
import com.javPOL.magazineJava.model.User;

import java.util.List;

public interface OrderService {
    void save(CreateOrderRequestDto request);

    void update(Order order);

    void delete(Order order);

    Order findById(int id);

    List<Order> findAll();

    List<Order> findAllByUser(User user);

    List<Order> findAllByCustomerId(Long customerId);
}
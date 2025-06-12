package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dto.CreateOrderRequestDto;
import com.javPOL.magazineJava.dto.OrderDto;
import com.javPOL.magazineJava.model.Order;
import com.javPOL.magazineJava.model.User;

import java.util.List;

public interface OrderService {
    void save(CreateOrderRequestDto request, User currentUser);

    void update(Order order);

    void delete(Order order);

    Order findById(int id);

    List<Order> findAll();

    List<OrderDto> findAllByUser(User user);

    List<Order> findAllByCustomerId(Long customerId);
}
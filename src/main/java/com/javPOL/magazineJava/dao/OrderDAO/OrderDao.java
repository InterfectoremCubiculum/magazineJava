package com.javPOL.magazineJava.dao.OrderDAO;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.Order;
import com.javPOL.magazineJava.model.User;

import java.util.List;

public interface OrderDao extends Dao<Order, Integer> {
    List<Order> findAllByUser(User user);

    List<Order> findAllByCustomerId(Long customerId);

    @Deprecated
    List<Order> findAllByUserId(Long id);
}

package com.javPOL.magazineJava.dao.OrderDAO;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.Order;

import java.util.List;

public interface OrderDao extends Dao<Order, Integer> {
    List<Order> findAllByUserId(Long id);
}

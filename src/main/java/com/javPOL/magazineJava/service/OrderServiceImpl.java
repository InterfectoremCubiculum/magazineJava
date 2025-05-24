package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.OrderDAO.OrderDao;
import com.javPOL.magazineJava.model.Order;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Transactional
    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Transactional
    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }
}

package com.javPOL.magazineJava.dao.OrderDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends DaoImpl<Order, Integer> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }
    @Override
    public List<Order> findAllByUserId(Long id) {
        List<Order> orders = entityManager
                .createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class)
                .setParameter("customerId", id)
                .getResultList();
        return orders;
    }
}

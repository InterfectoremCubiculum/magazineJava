package com.javPOL.magazineJava.dao.OrderDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Order;
import com.javPOL.magazineJava.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends DaoImpl<Order, Integer> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return entityManager
                .createQuery("SELECT o FROM Order o WHERE o.customer.user = :user", Order.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Order> findAllByCustomerId(Long customerId) {
        return entityManager
                .createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    @Deprecated
    public List<Order> findAllByUserId(Long id) {
        return findAllByCustomerId(id);
    }
}

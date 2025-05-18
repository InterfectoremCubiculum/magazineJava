package com.javPOL.magazineJava.dao.OrderDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends DaoImpl<Order, Integer> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }
}

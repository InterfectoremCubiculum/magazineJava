package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CustomerDAO.CustomerDao;
import com.javPOL.magazineJava.dao.OrderDAO.OrderDao;
import com.javPOL.magazineJava.dao.ProductDAO.ProductDao;
import com.javPOL.magazineJava.dao.ProductOrderDAO.ProductOrderDao;
import com.javPOL.magazineJava.dto.CreateOrderRequestDto;
import com.javPOL.magazineJava.dto.ProductOrderDto;
import com.javPOL.magazineJava.model.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CustomerDao customerDao;

    @Transactional
    @Override
    public void save(CreateOrderRequestDto request) {
        Customer customer = customerDao.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        orderDao.save(order);

        for (ProductOrderDto poDto : request.getProducts()) {
            Product product = productDao.findById(poDto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            ProductOrder productOrder  = new ProductOrder();
            productOrder.setOrder(order);
            productOrder.setProduct(product);
            productOrder.setUnityValue(product.getPrice());
            productOrder.setQuantity(poDto.getQuantity());

            productOrderDao.save(productOrder);
        }
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

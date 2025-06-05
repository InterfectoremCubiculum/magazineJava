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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info("Attempting to save an order for customer with ID: {}", request.getCustomerId());
        Customer customer = customerDao.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        orderDao.save(order);
        log.info("Order successfully saved with ID: {}", order.getId());

        for (ProductOrderDto poDto : request.getProducts()) {
            Product product = productDao.findById(poDto.getProductId())
                    .orElseThrow(() -> {
                        log.error("Product not found with ID: {}", poDto.getProductId());
                        return new IllegalArgumentException("Product not found");
                    });

            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrder(order);
            productOrder.setProduct(product);
            productOrder.setUnityValue(product.getPrice());
            productOrder.setQuantity(poDto.getQuantity());
            productOrderDao.save(productOrder);
            log.info("ProductOrder saved for product ID {} in order ID {}", poDto.getProductId(), order.getId());
        }
    }

    @Transactional
    @Override
    public void update(Order order) {
        log.info("Updating order with ID: {}", order.getId());
        orderDao.update(order);
        log.info("Order updated successfully for ID: {}", order.getId());
    }

    @Transactional
    @Override
    public void delete(Order order) {
        log.warn("Deleting order with ID: {}", order.getId());
        orderDao.delete(order);
        log.warn("Order deleted successfully with ID: {}", order.getId());
    }

    @Override
    public Order findById(int id) {
        log.debug("Fetching order with ID: {}", id);
        return orderDao.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", id);
                    return new EntityNotFoundException("Order not found with id: " + id);
                });
    }

    @Override
    public List<Order> findAll() {
        log.debug("Fetching all orders.");
        List<Order> orders = orderDao.findAll();
        log.info("Total orders fetched: {}", orders.size());
        return orders;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderDao.findAllByUser(user);
    }

    @Override
    public List<Order> findAllByCustomerId(Long customerId) {
        return orderDao.findAllByCustomerId(customerId);
    }
}
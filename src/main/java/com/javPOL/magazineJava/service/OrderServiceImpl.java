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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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
        logger.info("Attempting to save an order for customer with ID: {}", request.getCustomerId());
        Customer customer = customerDao.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        orderDao.save(order);
        logger.info("Order successfully saved with ID: {}", order.getId());

        for (ProductOrderDto poDto : request.getProducts()) {
            Product product = productDao.findById(poDto.getProductId())
                    .orElseThrow(() -> {
                        logger.error("Product not found with ID: {}", poDto.getProductId());
                        return new IllegalArgumentException("Product not found");
                    });

            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrder(order);
            productOrder.setProduct(product);
            productOrder.setUnityValue(product.getPrice());
            productOrder.setQuantity(poDto.getQuantity());
            productOrderDao.save(productOrder);
            logger.info("ProductOrder saved for product ID {} in order ID {}", poDto.getProductId(), order.getId());
        }
    }

    @Transactional
    @Override
    public void update(Order order) {
        logger.info("Updating order with ID: {}", order.getId());
        orderDao.update(order);
        logger.info("Order updated successfully for ID: {}", order.getId());
    }

    @Transactional
    @Override
    public void delete(Order order) {
        logger.warn("Deleting order with ID: {}", order.getId());
        orderDao.delete(order);
        logger.warn("Order deleted successfully with ID: {}", order.getId());
    }

    @Override
    public Order findById(int id) {
        logger.debug("Fetching order with ID: {}", id);
        return orderDao.findById(id)
                .orElseThrow(() -> {
                    logger.error("Order not found with ID: {}", id);
                    return new EntityNotFoundException("Order not found with id: " + id);
                });
    }

    @Override
    public List<Order> findAll() {
        logger.debug("Fetching all orders.");
        List<Order> orders = orderDao.findAll();
        logger.info("Total orders fetched: {}", orders.size());
        return orders;
    }

    @Override
    public List<Order> findAllByCustomerId(Long id)
    {
        logger.debug("Fetching all orders.");
        List<Order> orders = orderDao.findAllByUserId(id);
        logger.info("Total orders fetched: {}", orders.size());
        return orders;
    }
}
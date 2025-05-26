package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.ProductOrderDAO.ProductOrderDao;
import com.javPOL.magazineJava.model.ProductOrder;
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
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderDao productOrderDao;

    @Transactional
    @Override
    public void save(ProductOrder productOrder) {
        log.info("Attempting to save product order for product ID: {}", productOrder.getProduct().getId());
        productOrderDao.save(productOrder);
        log.info("ProductOrder saved successfully for product ID: {}", productOrder.getProduct().getId());
    }

    @Transactional
    @Override
    public void update(ProductOrder productOrder) {
        log.info("Updating product order with ID: {}", productOrder.getId());
        productOrderDao.update(productOrder);
        log.info("ProductOrder updated successfully with ID: {}", productOrder.getId());
    }

    @Transactional
    @Override
    public void delete(ProductOrder productOrder) {
        log.warn("Deleting product order with ID: {}", productOrder.getId());
        productOrderDao.delete(productOrder);
        log.warn("ProductOrder deleted with ID: {}", productOrder.getId());
    }

    @Transactional
    @Override
    public ProductOrder findById(int id) {
        log.debug("Fetching product order with ID: {}", id);
        return productOrderDao.findById(id)
                .orElseThrow(() -> {
                    log.error("ProductOrder not found with ID: {}", id);
                    return new EntityNotFoundException("ProductOrder not found with id: " + id);
                });
    }

    @Override
    public List<ProductOrder> findAll() {
        log.debug("Fetching all product orders.");
        List<ProductOrder> productOrders = productOrderDao.findAll();
        log.info("Total product orders fetched: {}", productOrders.size());
        return productOrders;
    }
}
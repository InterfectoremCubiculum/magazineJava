package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.ProductOrderDAO.ProductOrderDao;
import com.javPOL.magazineJava.model.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderDao productOrderDao;

    @Override
    public void save(ProductOrder productOrder) {
        productOrderDao.save(productOrder);
    }

    @Override
    public void update(ProductOrder productOrder) {
        productOrderDao.update(productOrder);
    }

    @Override
    public void delete(ProductOrder productOrder) {
        productOrderDao.delete(productOrder);
    }

    @Override
    public ProductOrder findById(int id) {
        return productOrderDao.findById(id);
    }

    @Override
    public List<ProductOrder> findAll() {
        return productOrderDao.findAll();
    }
}

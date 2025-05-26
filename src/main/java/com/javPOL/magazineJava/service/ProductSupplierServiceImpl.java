package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.ProductSupplierDAO.ProductSupplierDao;
import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.model.ProductSupplierId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSupplierServiceImpl implements ProductSupplierService {

    @Autowired
    private ProductSupplierDao productSupplierDao;

    @Override
    public void save(ProductSupplier productSupplier) {
        productSupplierDao.save(productSupplier);
    }

    @Override
    public void update(ProductSupplier productSupplier) {
        productSupplierDao.update(productSupplier);
    }

    @Override
    public void delete(ProductSupplier productSupplier) {
        productSupplierDao.delete(productSupplier);
    }

    @Override
    public ProductSupplier findById(ProductSupplierId id) {
        return productSupplierDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProductSupplier not found with id: " + id));
    }

    @Override
    public List<ProductSupplier> findAll() {
        return productSupplierDao.findAll();
    }
}

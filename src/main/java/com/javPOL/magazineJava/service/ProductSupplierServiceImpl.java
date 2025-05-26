package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.ProductSupplierDAO.ProductSupplierDao;
import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.model.ProductSupplierId;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductSupplierServiceImpl implements ProductSupplierService {


    @Autowired
    private ProductSupplierDao productSupplierDao;

    @Override
    public void save(ProductSupplier productSupplier) {
        log.info("Saving a product-supplier relationship with ID: {}", productSupplier.getId());
        productSupplierDao.save(productSupplier);
        log.info("ProductSupplier relationship saved successfully with ID: {}", productSupplier.getId());
    }

    @Override
    public void update(ProductSupplier productSupplier) {
        log.info("Updating a product-supplier relationship with ID: {}", productSupplier.getId());
        productSupplierDao.update(productSupplier);
        log.info("ProductSupplier relationship updated successfully with ID: {}", productSupplier.getId());
    }

    @Override
    public void delete(ProductSupplier productSupplier) {
        log.warn("Deleting a product-supplier relationship with ID: {}", productSupplier.getId());
        productSupplierDao.delete(productSupplier);
        log.warn("ProductSupplier relationship deleted successfully with ID: {}", productSupplier.getId());
    }

    @Override
    public ProductSupplier findById(ProductSupplierId id) {
        log.debug("Fetching product-supplier relationship with ID: {}", id);
        return productSupplierDao.findById(id)
                .orElseThrow(() -> {
                    log.error("ProductSupplier relationship not found with ID: {}", id);
                    return new EntityNotFoundException("ProductSupplier not found with id: " + id);
                });
    }

    @Override
    public List<ProductSupplier> findAll() {
        log.debug("Fetching all product-supplier relationships.");
        List<ProductSupplier> productSuppliers = productSupplierDao.findAll();
        log.info("Total product-supplier relationships fetched: {}", productSuppliers.size());
        return productSuppliers;
    }
}
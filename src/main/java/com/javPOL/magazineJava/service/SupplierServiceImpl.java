package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.SupplierDAO.SupplierDao;
import com.javPOL.magazineJava.model.Supplier;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j; // Automatyczne dodanie loggera
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Override
    public void save(Supplier supplier) {
        log.info("Saving supplier with name: {}", supplier.getName());
        supplierDao.save(supplier);
        log.info("Supplier saved successfully with ID: {}", supplier.getId());
    }

    @Override
    public void update(Supplier supplier) {
        log.info("Updating supplier with ID: {}", supplier.getId());
        supplierDao.update(supplier);
        log.info("Supplier updated successfully with ID: {}", supplier.getId());
    }

    @Override
    public void delete(Supplier supplier) {
        log.warn("Deleting supplier with ID: {}", supplier.getId());
        supplierDao.delete(supplier);
        log.warn("Supplier deleted successfully with ID: {}", supplier.getId());
    }

    @Override
    public Supplier findById(Long id) {
        log.debug("Fetching supplier with ID: {}", id);
        return supplierDao.findById(id)
                .orElseThrow(() -> {
                    log.error("Supplier not found with ID: {}", id);
                    return new EntityNotFoundException("Supplier not found with id: " + id);
                });
    }

    @Override
    public List<Supplier> findAll() {
        log.info("Fetching all suppliers");
        List<Supplier> suppliers = supplierDao.findAll();
        log.info("Total suppliers fetched: {}", suppliers.size());
        return suppliers;
    }
}
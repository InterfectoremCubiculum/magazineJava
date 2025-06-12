package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.WarehouseDAO.WarehouseDao;
import com.javPOL.magazineJava.model.Warehouse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j; // Automatyczne tworzenie loggera
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseDao warehouseDao;

    public WarehouseServiceImpl(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @Transactional
    @Override
    public Warehouse create(Warehouse warehouse) {
        log.info("Attempting to create a new warehouse: {}", warehouse.getName());
        warehouseDao.save(warehouse);
        log.info("Warehouse created successfully with ID: {}", warehouse.getId());
        return warehouse;
    }

    @Transactional
    @Override
    public void update(Warehouse warehouse) {
        log.info("Updating warehouse with ID: {}", warehouse.getId());
        warehouseDao.update(warehouse);
        log.info("Warehouse updated successfully with ID: {}", warehouse.getId());
    }

    @Transactional
    @Override
    public void delete(Warehouse warehouse) {
        log.warn("Deleting warehouse with ID: {}", warehouse.getId());
        warehouseDao.delete(warehouse);
        log.warn("Warehouse deleted successfully with ID: {}", warehouse.getId());
    }

    @Override
    public Warehouse findById(Long id) {
        log.debug("Fetching warehouse with ID: {}", id);
        return warehouseDao.findById(id)
                .orElseThrow(() -> {
                    log.error("Warehouse not found with ID: {}", id);
                    return new EntityNotFoundException("Warehouse not found with id: " + id);
                });
    }

    @Override
    public List<Warehouse> findAll() {
        log.debug("Fetching all warehouses.");
        List<Warehouse> warehouses = warehouseDao.findAll();
        log.info("Total warehouses fetched: {}", warehouses.size());
        return warehouses;
    }
}
package com.javPOL.magazineJava.service;
import com.javPOL.magazineJava.dao.WarehouseDAO.WarehouseDao;
import com.javPOL.magazineJava.model.Warehouse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    private WarehouseDao warehouseDao;

    @Transactional
    @Override
    public Warehouse create(Warehouse warehouse) {
        warehouseDao.save(warehouse);
        return warehouse;
    }

    @Transactional
    @Override
    public void update(Warehouse warehouse) {
        warehouseDao.update(warehouse);
    }

    @Transactional
    @Override
    public void delete(Warehouse warehouse) {
        warehouseDao.delete(warehouse);
    }

    @Override
    public Warehouse findById(Long id) {
        return warehouseDao.findById(id);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseDao.findAll();
    }
}

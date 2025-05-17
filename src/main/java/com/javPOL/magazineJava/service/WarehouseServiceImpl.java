package com.javPOL.magazineJava.service;
import com.javPOL.magazineJava.dao.WarehouseDAO.WarehouseDao;
import com.javPOL.magazineJava.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public Warehouse create(Warehouse warehouse) {
        warehouseDao.save(warehouse);
        return warehouse;
    }

    @Override
    public void update(Warehouse warehouse) {
        warehouseDao.update(warehouse);
    }

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

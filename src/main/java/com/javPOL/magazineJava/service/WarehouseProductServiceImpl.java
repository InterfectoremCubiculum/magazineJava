package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.WarehouseProductDAO.WarehouseProductDao;
import com.javPOL.magazineJava.model.WarehouseProduct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WarehouseProductServiceImpl implements WarehouseProductService {

    private final WarehouseProductDao warehouseProductDao;

    public WarehouseProductServiceImpl(WarehouseProductDao warehouseProductDao) {
        this.warehouseProductDao = warehouseProductDao;
    }

    @Override
    public Page<WarehouseProduct> findAll(Pageable page, Long warehouseId, Long categoryId) {
        log.info("Attempting to get all product in a warehouse Id: {}",warehouseId);
        var result = warehouseProductDao.findAll(page, warehouseId, categoryId);
        log.info("Total warehouses product fetched {}",result.getTotalElements());
        return result;
    }

    @Transactional
    @Override
    public void update(Long warehouseId, Long productId, Integer quantity) {
        warehouseProductDao.update(warehouseId, productId, quantity);
    }
}

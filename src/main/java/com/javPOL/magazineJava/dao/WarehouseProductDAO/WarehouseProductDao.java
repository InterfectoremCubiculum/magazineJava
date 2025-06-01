package com.javPOL.magazineJava.dao.WarehouseProductDAO;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.WarehouseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseProductDao extends Dao<WarehouseProduct, Long> {
    Page<WarehouseProduct> findAll(Pageable pageable, Long warehouseId, Long categoryId);
    Void update(Long warehouseId, Long productId, Integer quantity);
}

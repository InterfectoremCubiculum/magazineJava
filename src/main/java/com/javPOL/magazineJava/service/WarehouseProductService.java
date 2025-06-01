package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.WarehouseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseProductService {
    Page<WarehouseProduct> findAll(Pageable page, Long warehouseId, Long categoryId);
    void update(Long warehouseId, Long productId, Integer quantity);
}

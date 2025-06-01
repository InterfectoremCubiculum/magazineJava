package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.WarehouseProduct;
import com.javPOL.magazineJava.service.WarehouseProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/warehousesProducts")
public class WarehouseProductsController {

    private final WarehouseProductService warehouseProductService;

    public WarehouseProductsController(WarehouseProductService warehouseProductService){
        this.warehouseProductService = warehouseProductService;
    }

    @GetMapping(params = { "page", "size", "warehouseId", "categoryId"})
    public Page<WarehouseProduct> get(@PageableDefault() Pageable pageable, @RequestParam Long warehouseId, @RequestParam Long categoryId) {
        log.info("Get all products in warehouse pageable with category filter");
        var page =  warehouseProductService.findAll(pageable, warehouseId, categoryId);

        log.info("Products found. Total quantity: {}, Page: {}", page.getTotalElements(), page.getTotalPages());
        return page;
    }

    @PatchMapping("/{warehouseId}/{productId}/{quantity}")
    public void update(@PathVariable Long warehouseId, @PathVariable Long productId, @PathVariable Integer quantity) {
        log.info("Patch productId: {}, quantity: {} in warehouseId: {}", productId, quantity, warehouseId);
        warehouseProductService.update(warehouseId, productId, quantity);
    }
}

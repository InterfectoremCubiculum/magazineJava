package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.dto.WarehouseDto;
import com.javPOL.magazineJava.model.Warehouse;
import com.javPOL.magazineJava.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getAll() {
        return warehouseService.findAll();
    }

    @GetMapping("/{id}")
    public Warehouse get(@PathVariable Long id) {
        return warehouseService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseService.create(
                new Warehouse(
                        null,
                        warehouseDto.getName(),
                        warehouseDto.getLocation()
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(warehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody WarehouseDto warehouseDto) {
        warehouseService.update(new Warehouse(
                id,
                warehouseDto.getName(),
                warehouseDto.getLocation()
        ));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.findById(id);
        if (warehouse == null) {
            return ResponseEntity.notFound().build();
        }
        warehouseService.delete(warehouse);
        return ResponseEntity.noContent().build();
    }
}

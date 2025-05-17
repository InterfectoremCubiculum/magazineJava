package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse create(Warehouse warehouse);
    void update(Warehouse warehouse);
    void delete(Warehouse warehouse);
    Warehouse findById(Long id);
    List<Warehouse> findAll();
}

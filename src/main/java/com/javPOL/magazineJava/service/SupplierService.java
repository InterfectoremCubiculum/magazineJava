package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Supplier;

import java.util.List;

public interface SupplierService {
    void save(Supplier supplier);
    void update(Supplier supplier);
    void delete(Supplier supplier);
    Supplier findById(Long id);
    List<Supplier> findAll();
}

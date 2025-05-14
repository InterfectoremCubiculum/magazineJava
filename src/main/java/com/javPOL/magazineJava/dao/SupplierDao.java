package com.javPOL.magazineJava.dao;

import com.javPOL.magazineJava.model.Supplier;

import java.util.List;

public interface SupplierDao {
    Supplier findById(Integer id);
    List<Supplier> findAll();
    void save(Supplier supplier);
    void update(Supplier supplier);
    void deleteById(Integer id);
}


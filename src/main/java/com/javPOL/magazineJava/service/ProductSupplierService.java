package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.model.ProductSupplierId;

import java.util.List;

public interface ProductSupplierService {
    void save(ProductSupplier productSupplier);
    void update(ProductSupplier productSupplier);
    void delete(ProductSupplier productSupplier);
    ProductSupplier findById(ProductSupplierId id);
    List<ProductSupplier> findAll();
}

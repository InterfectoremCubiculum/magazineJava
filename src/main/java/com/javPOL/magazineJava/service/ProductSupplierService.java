package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.ProductSupplier;
import java.util.List;
import java.util.Optional;

public interface ProductSupplierService {
    List<ProductSupplier> getAll();
    Optional<ProductSupplier> getById(int id);
    ProductSupplier create(ProductSupplier ps);
    ProductSupplier update(int id, ProductSupplier ps);
    void delete(int id);
}

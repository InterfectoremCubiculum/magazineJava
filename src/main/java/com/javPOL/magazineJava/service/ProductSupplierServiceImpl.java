package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.repository.ProductSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSupplierServiceImpl implements ProductSupplierService {

    private final ProductSupplierRepository repo;

    @Autowired
    public ProductSupplierServiceImpl(ProductSupplierRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ProductSupplier> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<ProductSupplier> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public ProductSupplier create(ProductSupplier ps) {
        return repo.save(ps);
    }

    @Override
    public ProductSupplier update(int id, ProductSupplier psDetails) {
        return repo.findById(id).map(ps -> {
            ps.setUnitValue(psDetails.getUnitValue());
            ps.setProduct(psDetails.getProduct());
            ps.setSupplier(psDetails.getSupplier());
            return repo.save(ps);
        }).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}


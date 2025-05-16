package com.javPOL.magazineJava.dao.ProductSupplierDAO;

import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.model.ProductSupplierId;
import org.springframework.stereotype.Repository;
import com.javPOL.magazineJava.dao.DaoImpl;

@Repository
public class ProductSupplierDaoImpl extends DaoImpl<ProductSupplier, ProductSupplierId> implements ProductSupplierDao {
    public ProductSupplierDaoImpl() {
        super(ProductSupplier.class);
    }
}
package com.javPOL.magazineJava.dao.SupplierDAO;
import com.javPOL.magazineJava.dao.DaoImpl;

import com.javPOL.magazineJava.model.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierDaoImpl extends DaoImpl<Supplier, Integer> implements SupplierDao {
    public SupplierDaoImpl() {
        super(Supplier.class);
    }
}

package com.javPOL.magazineJava.dao.WarehouseProductDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.WarehouseProduct;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseProductDaoImpl extends DaoImpl<WarehouseProduct, Long> implements WarehouseProductDao {
    public WarehouseProductDaoImpl() {
        super(WarehouseProduct.class);
    }
}

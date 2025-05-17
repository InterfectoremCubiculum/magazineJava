package com.javPOL.magazineJava.dao.WarehouseProductDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.WarehouseProduct;

public class WarehouseProductDaoImpl extends DaoImpl<WarehouseProduct, Integer> implements WarehouseProductDao {
    public WarehouseProductDaoImpl() {
        super(WarehouseProduct.class);
    }
}

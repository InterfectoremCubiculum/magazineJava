package com.javPOL.magazineJava.dao.WarehouseDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Warehouse;

public class WarehouseDaoImpl extends DaoImpl<Warehouse, Integer> implements WarehouseDao {
    public WarehouseDaoImpl() {
        super(Warehouse.class);
    }
}

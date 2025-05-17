package com.javPOL.magazineJava.dao.WarehouseDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Warehouse;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseDaoImpl extends DaoImpl<Warehouse, Long> implements WarehouseDao {
    public WarehouseDaoImpl() {
        super(Warehouse.class);
    }
}

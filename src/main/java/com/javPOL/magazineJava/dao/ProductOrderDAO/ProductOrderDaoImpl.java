package com.javPOL.magazineJava.dao.ProductOrderDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.ProductOrder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOrderDaoImpl extends DaoImpl<ProductOrder, Integer> implements ProductOrderDao {
    public ProductOrderDaoImpl() {
        super(ProductOrder.class);
    }
}
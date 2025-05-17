package com.javPOL.magazineJava.dao.ProductDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Product;

public class ProductDaoImpl extends DaoImpl<Product, Integer> implements ProductDao {
    public ProductDaoImpl() {
        super(Product.class);
    }
}

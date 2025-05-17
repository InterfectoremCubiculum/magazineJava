package com.javPOL.magazineJava.dao.ProductDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends DaoImpl<Product, Long> implements ProductDao {
    public ProductDaoImpl() {
        super(Product.class);
    }
}

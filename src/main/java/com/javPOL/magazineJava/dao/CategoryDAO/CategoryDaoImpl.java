package com.javPOL.magazineJava.dao.CategoryDAO;
import com.javPOL.magazineJava.dao.DaoImpl;

import com.javPOL.magazineJava.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends DaoImpl<Category, Long> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }
}

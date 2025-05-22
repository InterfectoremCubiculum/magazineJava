package com.javPOL.magazineJava.repository.ProductRepository;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.repository.RepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.javPOL.magazineJava.util.OrderByClauseBuilder.buildOrderByClause;

@Repository
public class ProductRepositoryImpl extends RepositoryImpl<Product, Long> implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public ProductRepositoryImpl(Dao<Product, Long> dao) {
        super(dao);
    }

    @Override
    public Page<Product> findAll(Pageable pageable, Long categoryId) {
        String orderByClause = buildOrderByClause(pageable);
        String baseQuery = "SELECT e FROM " + Product.class.getName() + " e" + " WHERE e.category.id = :categoryId";
        List<Product> content = entityManager
                .createQuery(baseQuery + orderByClause, Product.class)
                .setParameter("categoryId", categoryId)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long count = count();
        return new PageImpl<>(content, pageable, count);
    }
}

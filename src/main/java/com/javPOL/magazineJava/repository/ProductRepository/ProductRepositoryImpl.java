package com.javPOL.magazineJava.repository.ProductRepository;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.repository.RepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
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

    public ProductRepositoryImpl(Dao<Product, Long> dao ) {
        super(dao);
    }

    @Override
    public Page<Product> findAll(Pageable pageable, Long categoryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cr = cb.createQuery(Product.class);
        Root<Product> root = cr.from(Product.class);
        Predicate categoryPredicate = cb.equal(root.get("category").get("id"), categoryId);
        cr.select(root).where(categoryPredicate);

        cr.orderBy(buildOrderByClause(pageable, cb, root));

        List<Product> content = entityManager
                .createQuery(cr)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(cb.count(countRoot)).where(cb.equal(countRoot.get("category").get("id"), categoryId));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(content, pageable, count);
    }
}

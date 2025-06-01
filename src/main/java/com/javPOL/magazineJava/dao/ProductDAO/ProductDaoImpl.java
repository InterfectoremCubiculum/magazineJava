package com.javPOL.magazineJava.dao.ProductDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.javPOL.magazineJava.util.OrderByClauseBuilder.buildOrderByClause;

@Repository
public class ProductDaoImpl extends DaoImpl<Product, Long> implements ProductDao {
    public ProductDaoImpl() {
        super(Product.class);
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

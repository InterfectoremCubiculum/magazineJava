package com.javPOL.magazineJava.dao.WarehouseProductDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.WarehouseProduct;
import com.javPOL.magazineJava.model.WarehouseProductId;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.javPOL.magazineJava.util.OrderByClauseBuilder.buildOrderByClause;

@Repository
public class WarehouseProductDaoImpl extends DaoImpl<WarehouseProduct, Long> implements WarehouseProductDao {
    public WarehouseProductDaoImpl()
    {
        super(WarehouseProduct.class);
    }

    @Override
    public List<WarehouseProduct> findAll() {
        return entityManager
                .createQuery("SELECT e FROM " + WarehouseProduct.class.getName() + " e", WarehouseProduct.class)
                .getResultList();
    }

    @Override
    public Page<WarehouseProduct> findAll(Pageable pageable, Long warehouseId, Long categoryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<WarehouseProduct> cq = cb.createQuery(WarehouseProduct.class);
        Root<WarehouseProduct> root = cq.from(WarehouseProduct.class);
        Predicate categoryPredicate = cb.equal(root.get("product").get("category").get("id"), categoryId);
        Predicate idPredicate = cb.equal(root.get("warehouse").get("id"), warehouseId);
        cq.select(root).where(idPredicate).where(categoryPredicate);
        cq.orderBy(buildOrderByClause(pageable, cb, root));

        List<WarehouseProduct> content = entityManager
                .createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<WarehouseProduct> countRoot = countQuery.from(WarehouseProduct.class);
        countQuery.select(cb.count(countRoot));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(content, pageable, count);
    }

    @Transactional
    @Override
    public Void update(Long warehouseId, Long productId, Integer quantity) {
        WarehouseProductId id = new WarehouseProductId(warehouseId, productId);
        WarehouseProduct warehouseProduct = entityManager.find(WarehouseProduct.class, id);
        warehouseProduct.setQuantity(quantity);
        return null;
    }
}

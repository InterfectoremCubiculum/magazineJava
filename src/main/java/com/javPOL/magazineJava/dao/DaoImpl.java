package com.javPOL.magazineJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.javPOL.magazineJava.util.OrderByClauseBuilder.buildOrderByClause;

public abstract class DaoImpl<T, ID> implements Dao<T,ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public DaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    @Override
    public void save(Object entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Object entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Object entity) {
        entityManager.remove(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return entityManager
            .createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass)
            .getResultList();
    }
    @Override
    public Page<T> findAll(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cr = cb.createQuery(entityClass);
        Root<T> root = cr.from(entityClass);

        cr.orderBy(buildOrderByClause(pageable, cb, root));
        List<T> content = entityManager
                .createQuery(cr)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        long count = count();

        return new PageImpl<>(content, pageable, count);
    }

    @Override
    public long count() {
        return entityManager
                .createQuery("SELECT COUNT(*) FROM " + entityClass.getName(), Long.class)
                .getSingleResult();
    }

}

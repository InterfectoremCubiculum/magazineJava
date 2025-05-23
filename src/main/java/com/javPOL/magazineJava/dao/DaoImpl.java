package com.javPOL.magazineJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.javPOL.magazineJava.util.OrderByClauseBuilder.buildOrderByClause;

public abstract class DaoImpl<T, ID> implements Dao<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    public DaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager
                .createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        String orderByClause = buildOrderByClause(pageable);
        String baseQuery = "SELECT e FROM " + entityClass.getName() + " e";
        List<T> content = entityManager
                .createQuery(baseQuery + orderByClause, entityClass)
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

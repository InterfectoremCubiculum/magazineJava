package com.javPOL.magazineJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

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
    public long count() {
        return entityManager
                .createQuery("SELECT COUNT(*) FROM " + entityClass.getName(), Long.class)
                .getSingleResult();
    }
}

package com.javPOL.magazineJava.dao;

import java.util.List;

public interface Dao<T, ID> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();
    long count();
}

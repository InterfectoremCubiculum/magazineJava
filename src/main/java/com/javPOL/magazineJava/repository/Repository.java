package com.javPOL.magazineJava.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Repository<T, ID> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    long count();
}

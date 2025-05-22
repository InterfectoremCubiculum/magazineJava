package com.javPOL.magazineJava.repository;

import com.javPOL.magazineJava.dao.Dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class RepositoryImpl<T, ID> implements Repository<T, ID>{
    private final Dao<T, ID> dao;

    public RepositoryImpl(Dao<T, ID> dao) {
        this.dao = dao;
    }
    @Override
    public void save(T entity) {
        dao.save(entity);
    }
    @Override
    public void update(T entity) {
        dao.update(entity);
    }
    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }
    @Override
    public T findById(ID id) {
        return dao.findById(id);
    }
    @Override
    public List<T> findAll() {
        return dao.findAll();
    }
    @Override
    public Page<T> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }
    @Override
    public long count() {
        return dao.count();
    }
}

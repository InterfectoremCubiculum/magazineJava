    package com.javPOL.magazineJava.dao;

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;

    import java.util.List;

    public interface Dao<T, ID> {
        void save(T entity);
        void update(T entity);
        void delete(T entity);
        T findById(ID id);
        List<T> findAll();
        Page<T> findAll(Pageable pageable);
        long count();
    }

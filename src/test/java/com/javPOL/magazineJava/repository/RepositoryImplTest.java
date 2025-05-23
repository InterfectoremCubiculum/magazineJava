package com.javPOL.magazineJava.repository;

import com.javPOL.magazineJava.dao.Dao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public abstract class RepositoryImplTest<T> {

    protected Dao<T, Long> daoMock;
    protected RepositoryImpl<T, Long> repository;
    protected abstract T createEntity();

    @Test
    void testSave() {
        T entity = createEntity();
        repository.save(entity);
        verify(daoMock).save(entity);
    }

    @Test
    void testUpdate() {
        T entity = createEntity();
        repository.update(entity);
        verify(daoMock).update(entity);
    }

    @Test
    void testDelete() {
        T entity = createEntity();
        repository.delete(entity);
        verify(daoMock).delete(entity);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        T entity = createEntity();
        when(daoMock.findById(id)).thenReturn(Optional.of(entity));
        Optional<T> result = repository.findById(id);
        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
        verify(daoMock).findById(id);
    }

    @Test
    void testFindByIdWhenNotFound() {
        Long id = 2L;
        when(daoMock.findById(id)).thenReturn(Optional.empty());
        Optional<T> result = repository.findById(id);
        assertFalse(result.isPresent());
        verify(daoMock).findById(id);
    }

    @Test
    void testFindAll() {
        List<T> entities = List.of(createEntity(), createEntity());
        when(daoMock.findAll()).thenReturn(entities);
        List<T> result = repository.findAll();
        assertEquals(entities, result);
        verify(daoMock).findAll();
    }

    @Test
    void testFindAllWithPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<T> page = new PageImpl<>(List.of(createEntity()));
        when(daoMock.findAll(pageable)).thenReturn(page);
        Page<T> result = repository.findAll(pageable);
        assertEquals(page, result);
        verify(daoMock).findAll(pageable);
    }

    @Test
    void testCount() {
        long count = 5L;
        when(daoMock.count()).thenReturn(count);
        long result = repository.count();
        assertEquals(count, result);
        verify(daoMock).count();
    }

}

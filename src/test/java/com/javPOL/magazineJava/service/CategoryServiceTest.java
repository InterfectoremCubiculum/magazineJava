package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CategoryDAO.CategoryDao;
import com.javPOL.magazineJava.model.Category;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testSave() {
        Category category = new Category();
        category.setName("Category A");

        categoryService.save(category);

        verify(categoryDao, times(1)).save(category);
    }

    @Test
    public void testUpdate() {
        Category category = new Category();
        category.setId(1L);

        categoryService.update(category);

        verify(categoryDao, times(1)).update(category);
    }

    @Test
    public void testDelete() {
        Category category = new Category();
        category.setId(1L);

        categoryService.delete(category);

        verify(categoryDao, times(1)).delete(category);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Category category = new Category();
        category.setId(id);
        when(categoryDao.findById(id)).thenReturn(Optional.of(category));

        Category found = categoryService.findById(id);

        assertEquals(category, found);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;
        when(categoryDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(id));
    }

    @Test
    public void testFindAll() {
        when(categoryDao.findAll()).thenReturn(List.of(new Category(), new Category()));

        List<Category> result = categoryService.findAll();

        assertEquals(2, result.size());
    }
}
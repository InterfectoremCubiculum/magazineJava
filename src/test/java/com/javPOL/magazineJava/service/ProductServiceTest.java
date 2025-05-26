package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CategoryDAO.CategoryDao;
import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Category;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.repository.ProductRepository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryDao categoryDao;

    // Wstrzykuje mocki do ProductServiceImpl
    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void testCreate() {

        // Przygotowanie danych
        ProductDto dto = new ProductDto(
                "Test Product",
                1.5, 100,
                "Test Description",
                1L
        );

        Category category = new Category(1L, "Test Category");

        // kiedy zostanie wykonana metoda  when(categoryDao.findById(1L)) to ma ona zwrócić
        // .thenReturn(category) obiekt category
        when(categoryDao.findById(1L)).thenReturn(Optional.of(category));

        // doAnswer możemy customowo zdefiniować zachowanie metody
        // invocation -> { ... } mówi co ma się stać jeżeli wykona sie metode save
        doAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(1L);
            return null;
        }).when(productRepository).save(any(Product.class));

        // Wykonanie testowanej metody
        Product result = productService.create(dto);

        // Sprawdzamy poprawność wykonania testowej metody
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Product", result.getName());
        assertEquals(1.5, result.getWeight());
        assertEquals(100.0, result.getPrice());
        assertEquals("Test Description", result.getDescription());
        assertEquals(category, result.getCategory());

        // pobiera przekazy argument do metody save
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        // sprawdza czy metoda save() została wywołana oraz przechwytuje pobrany agrument do niej
        verify(productRepository).save(captor.capture());

        // Sprawdza czy ten produkt ma taką samą nazwe co produkt który chcieliśmy stworzyć
        Product savedProduct = captor.getValue();
        assertEquals("Test Product", savedProduct.getName());

        // sprawdza czy metoda findById() została wykonana
        verify(categoryDao).findById(1L);
    }

    @Test
    public void testCreateWithNonExistingCategory() {
        ProductDto dto = new ProductDto(
                "Name",
                1.0,
                10,
                "Test Description",
                99L
        );
        when(categoryDao.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () ->
                productService.create(dto));

        assertEquals("Category not found with id: 99", ex.getMessage());
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        ProductDto dto = new ProductDto(
                "Updated Product",
                2.0,
                200,
                "Updated Description",
                2L
        );

        Category category = new Category(2L, "Test Category");

        when(categoryDao.findById(2L)).thenReturn(Optional.of(category));
        productService.update(id, dto);

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).update(captor.capture());

        Product updatedProduct = captor.getValue();
        assertEquals(id, updatedProduct.getId());
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(2.0, updatedProduct.getWeight());
        assertEquals(200.0, updatedProduct.getPrice());
        assertEquals("Updated Description", updatedProduct.getDescription());
        assertEquals(category, updatedProduct.getCategory());

        verify(categoryDao).findById(2L);
    }

    @Test
    public void testUpdateWithNonExistingCategory() {
        Long id = 1L;
        ProductDto dto = new ProductDto(
                "Updated Product",
                2.0,
                200,
                "Updated Description",
                99L
        );

        when(categoryDao.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(
                EntityNotFoundException.class,
                () -> productService.update(id, dto)
        );

        assertEquals("Category not found with id: 99", ex.getMessage());
        verify(categoryDao).findById(99L);
        verify(productRepository, never()).update(any());
    }

    @Test
    public void testDelete() {
        Product product = new Product();
        product.setId(1L);

        productService.delete(product);

        verify(productRepository).delete(product);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product result = productService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(productRepository).findById(id);
    }

    @Test
    public void testFindAll() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }

    @Test
    public void testFindAllWithPageable() {

        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = Arrays.asList(new Product(), new Product());
        Page<Product> page = new PageImpl<>(products, pageable, 2);
        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<Product> result = productService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        verify(productRepository).findAll(pageable);
    }

    @Test
    public void testFindAllWithPageableAndCategoryId() {
        Pageable pageable = PageRequest.of(0, 10);
        Long categoryId = 1L;
        List<Product> products = Arrays.asList(new Product(), new Product());
        Page<Product> page = new PageImpl<>(products, pageable, 2);
        when(productRepository.findAll(pageable, categoryId)).thenReturn(page);

        Page<Product> result = productService.findAll(pageable, categoryId);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        verify(productRepository).findAll(pageable, categoryId);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 999L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> productService.findById(id)
        );

        assertEquals("Product not found with id: " + id, exception.getMessage());

        verify(productRepository).findById(id);
    }

    @Test
    public void testFindAllEmpty() {
        when(productRepository.findAll()).thenReturn(emptyList());

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository).findAll();
    }

}

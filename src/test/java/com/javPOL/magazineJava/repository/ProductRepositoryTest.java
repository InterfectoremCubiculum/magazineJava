package com.javPOL.magazineJava.repository;

//import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.repository.ProductRepository.ProductRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Product> productQuery;

    @Mock
    private CriteriaQuery<Long> countQuery;

    @Mock
    private Root<Product> productRoot;

    @Mock
    private Root<Product> countRoot;

    @Mock
    private TypedQuery<Product> typedProductQuery;

    @Mock
    private TypedQuery<Long> typedCountQuery;

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(productRepository, "entityManager", entityManager);
    }

    @Test
    public void testFindAllWithCategoryId() {
        Pageable pageable = PageRequest.of(0, 10);
        Long categoryId = 1L;

        List<Product> productList = List.of(new Product());

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);

        @SuppressWarnings("unchecked")
        Path<Object> categoryPath = (Path<Object>) mock(Path.class);

        @SuppressWarnings("unchecked")
        Path<Object> idPath = (Path<Object>) mock(Path.class);

        @SuppressWarnings("unchecked")
        Path<Object> countCategoryPath = (Path<Object>) mock(Path.class);

        @SuppressWarnings("unchecked")
        Path<Object> countIdPath = (Path<Object>) mock(Path.class);

        when(criteriaBuilder.createQuery(Product.class)).thenReturn(productQuery);
        when(productQuery.from(Product.class)).thenReturn(productRoot);
        doReturn(categoryPath).when(productRoot).get("category");
        doReturn(idPath).when(categoryPath).get("id");

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.equal(any(), eq(categoryId))).thenReturn(predicate);
        when(productQuery.select(productRoot)).thenReturn(productQuery);
        when(productQuery.where(predicate)).thenReturn(productQuery);

        when(entityManager.createQuery(productQuery)).thenReturn(typedProductQuery);
        when(typedProductQuery.setFirstResult(anyInt())).thenReturn(typedProductQuery);
        when(typedProductQuery.setMaxResults(anyInt())).thenReturn(typedProductQuery);
        when(typedProductQuery.getResultList()).thenReturn(productList);

        when(criteriaBuilder.createQuery(Long.class)).thenReturn(countQuery);
        when(countQuery.from(Product.class)).thenReturn(countRoot);
        doReturn(countCategoryPath).when(countRoot).get("category");
        doReturn(countIdPath).when(countCategoryPath).get("id");

        when(countQuery.select(any())).thenReturn(countQuery);
        when(countQuery.where(any(Predicate.class))).thenReturn(countQuery);

        when(entityManager.createQuery(countQuery)).thenReturn(typedCountQuery);
        when(typedCountQuery.getSingleResult()).thenReturn(1L);

        Page<Product> result = productRepository.findAll(pageable, categoryId);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        verify(entityManager).getCriteriaBuilder();
    }
}


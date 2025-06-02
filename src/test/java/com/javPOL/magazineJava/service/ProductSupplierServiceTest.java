package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.ProductSupplierDAO.ProductSupplierDao;
import com.javPOL.magazineJava.model.ProductSupplier;
import com.javPOL.magazineJava.model.ProductSupplierId;
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
public class ProductSupplierServiceTest {

    @Mock
    private ProductSupplierDao productSupplierDao;

    @InjectMocks
    private ProductSupplierServiceImpl productSupplierService;

    @Test
    public void testSave() {
        ProductSupplier productSupplier = new ProductSupplier();
        productSupplier.setId(new ProductSupplierId(1L, 1L));

        productSupplierService.save(productSupplier);

        verify(productSupplierDao, times(1)).save(productSupplier);
    }

    @Test
    public void testUpdate() {
        ProductSupplier productSupplier = new ProductSupplier();
        productSupplier.setId(new ProductSupplierId(1L, 1L));

        productSupplierService.update(productSupplier);

        verify(productSupplierDao, times(1)).update(productSupplier);
    }

    @Test
    public void testDelete() {
        ProductSupplier productSupplier = new ProductSupplier();
        productSupplier.setId(new ProductSupplierId(1L, 1L));

        productSupplierService.delete(productSupplier);

        verify(productSupplierDao, times(1)).delete(productSupplier);
    }

    @Test
    public void testFindById() {
        ProductSupplierId id = new ProductSupplierId(1L, 1L);
        ProductSupplier productSupplier = new ProductSupplier();
        productSupplier.setId(id);
        when(productSupplierDao.findById(id)).thenReturn(Optional.of(productSupplier));

        ProductSupplier found = productSupplierService.findById(id);

        assertEquals(productSupplier, found);
    }

    @Test
    public void testFindByIdNotFound() {
        ProductSupplierId id = new ProductSupplierId(1L, 1L);
        when(productSupplierDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productSupplierService.findById(id));
    }

    @Test
    public void testFindAll() {
        when(productSupplierDao.findAll()).thenReturn(List.of(new ProductSupplier(), new ProductSupplier()));

        List<ProductSupplier> result = productSupplierService.findAll();

        assertEquals(2, result.size());
    }
}
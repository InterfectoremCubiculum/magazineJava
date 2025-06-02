package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.SupplierDAO.SupplierDao;
import com.javPOL.magazineJava.model.Supplier;
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
public class SupplierServiceTest {

    @Mock
    private SupplierDao supplierDao;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    public void testSave() {
        Supplier supplier = new Supplier();
        supplier.setName("Dostawy Valerego");

        supplierService.save(supplier);

        verify(supplierDao, times(1)).save(supplier);
    }

    @Test
    public void testUpdate() {
        Supplier supplier = new Supplier();
        supplier.setName("Dostawy Valerego");

        supplierService.update(supplier);

        verify(supplierDao, times(1)).update(supplier);
    }

    @Test
    public void testDelete() {
        Supplier supplier = new Supplier();
        supplier.setName("Dostawy Valerego");

        supplierService.delete(supplier);

        verify(supplierDao, times(1)).delete(supplier);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Supplier supplier = new Supplier();
        supplier.setId(id);
        when(supplierDao.findById(id)).thenReturn(Optional.of(supplier));

        Supplier found = supplierService.findById(id);

        assertEquals(supplier, found);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 2L;
        when(supplierDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> supplierService.findById(id));
    }

    @Test
    public void testFindAll() {
        when(supplierDao.findAll()).thenReturn(List.of(new Supplier(), new Supplier()));

        List<Supplier> result = supplierService.findAll();

        assertEquals(2, result.size());
    }
}

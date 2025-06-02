package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.InvoiceDAO.InvoiceDao;
import com.javPOL.magazineJava.model.Invoice;
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
public class InvoiceServiceTest {

    @Mock
    private InvoiceDao invoiceDao;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    public void testSave() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        invoiceService.save(invoice);

        verify(invoiceDao, times(1)).save(invoice);
    }

    @Test
    public void testUpdate() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        invoiceService.update(invoice);

        verify(invoiceDao, times(1)).update(invoice);
    }

    @Test
    public void testDelete() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        invoiceService.delete(invoice);

        verify(invoiceDao, times(1)).delete(invoice);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(id);
        when(invoiceDao.findById(id)).thenReturn(Optional.of(invoice));

        Invoice found = invoiceService.findById(id);

        assertEquals(invoice, found);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;
        when(invoiceDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> invoiceService.findById(id));
    }

    @Test
    public void testFindAll() {
        when(invoiceDao.findAll()).thenReturn(List.of(new Invoice(), new Invoice()));

        List<Invoice> result = invoiceService.findAll();

        assertEquals(2, result.size());
    }
}
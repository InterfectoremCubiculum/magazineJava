package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.InvoiceDAO.InvoiceDao;
import com.javPOL.magazineJava.model.Invoice;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j; // Automatyczne tworzenie loggera
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceDao invoiceDao;

    public InvoiceServiceImpl(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @Override
    public void save(Invoice invoice) {
        log.info("Saving invoice: {}", invoice);
        invoiceDao.save(invoice);
        log.info("Invoice saved successfully with ID: {}", invoice.getId());
    }

    @Override
    public void update(Invoice invoice) {
        log.info("Updating invoice with ID: {}", invoice.getId());
        invoiceDao.update(invoice);
        log.info("Invoice updated successfully with ID: {}", invoice.getId());
    }

    @Override
    public void delete(Invoice invoice) {
        log.warn("Deleting invoice with ID: {}", invoice.getId());
        invoiceDao.delete(invoice);
        log.warn("Invoice deleted successfully with ID: {}", invoice.getId());
    }

    @Override
    public Invoice findById(Long id) {
        log.debug("Fetching invoice with ID: {}", id);
        return invoiceDao.findById(id)
                .orElseThrow(() -> {
                    log.error("Invoice not found with ID:" + id);
                    return new EntityNotFoundException("Invoice not found with id: " + id);
                });
    }

    @Override
    public List<Invoice> findAll() {
        log.info("Fetching all invoices.");
        List<Invoice> invoices = invoiceDao.findAll();
        log.info("Total invoices fetched: {}", invoices.size());
        return invoices;
    }
}
package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.InvoiceDAO.InvoiceDao;
import com.javPOL.magazineJava.model.Invoice;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    public void save(Invoice invoice) {
        invoiceDao.save(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        invoiceDao.update(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceDao.delete(invoice);
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found with id: " + id));
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceDao.findAll();
    }
}

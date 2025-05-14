package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Invoice;
import com.javPOL.magazineJava.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repo;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Invoice> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Invoice> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public Invoice create(Invoice invoice) {
        return repo.save(invoice);
    }

    @Override
    public Invoice update(int id, Invoice updated) {
        return repo.findById(id).map(invoice -> {
            invoice.setCustomer(updated.getCustomer());
            invoice.setIssueDate(updated.getIssueDate());
            invoice.setSaleDate(updated.getSaleDate());
            invoice.setPaymentDueDate(updated.getPaymentDueDate());
            invoice.setPaymentMethod(updated.getPaymentMethod());
            return repo.save(invoice);
        }).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}

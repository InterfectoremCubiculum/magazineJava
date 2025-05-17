package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Invoice;

import java.util.List;

public interface InvoiceService {
    void save(Invoice invoice);
    void update(Invoice invoice);
    void delete(Invoice invoice);
    Invoice findById(Long id);
    List<Invoice> findAll();
}

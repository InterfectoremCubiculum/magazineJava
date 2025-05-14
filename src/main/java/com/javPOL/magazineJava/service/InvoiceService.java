package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> getAll();
    Optional<Invoice> getById(int id);
    Invoice create(Invoice invoice);
    Invoice update(int id, Invoice invoice);
    void delete(int id);
}

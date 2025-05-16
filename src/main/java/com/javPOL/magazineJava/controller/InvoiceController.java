package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.Invoice;
import com.javPOL.magazineJava.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getAll() {
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public Invoice getById(@PathVariable int id) {
        return invoiceService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Invoice invoice) {
        invoiceService.save(invoice);
    }

    @PutMapping
    public void update(@RequestBody Invoice invoice) {
        invoiceService.update(invoice);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Invoice invoice = invoiceService.findById(id);
        if (invoice != null) {
            invoiceService.delete(invoice);
        }
    }
}

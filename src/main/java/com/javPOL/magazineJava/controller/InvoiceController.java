package com.javPOL.magazineJava.controller;

import com.javPOL.magazineJava.model.Invoice;
import com.javPOL.magazineJava.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Invoice> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Invoice create(@RequestBody Invoice invoice) {
        return service.create(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable int id, @RequestBody Invoice invoice) {
        try {
            return ResponseEntity.ok(service.update(id, invoice));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

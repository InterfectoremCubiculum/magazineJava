package com.javPOL.magazineJava.repository;

import com.javPOL.magazineJava.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findBySupplierId(Integer supplierId);
    List<Invoice> findByEmployeeId(Integer employeeId);
}


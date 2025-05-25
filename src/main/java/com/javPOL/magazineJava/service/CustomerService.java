package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.model.Customer;
import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();
}
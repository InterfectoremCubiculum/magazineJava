package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CustomerDAO.CustomerDao;
import com.javPOL.magazineJava.model.Customer;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // Automatycznie tworzy statyczny logger
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerDao customerDao;

    @Override
    public void save(Customer customer) {
        log.info("Saving customer: {}", customer.getName());
        customerDao.save(customer);
        log.info("Customer saved successfully: {}", customer);
    }

    @Override
    public void update(Customer customer) {
        log.info("Updating customer with id: {}", customer.getId());
        customerDao.update(customer);
        log.info("Customer updated successfully with id: {}", customer.getId());
    }

    @Override
    public void delete(Customer customer) {
        log.warn("Deleting customer with id: {}", customer.getId());
        customerDao.delete(customer);
        log.warn("Customer deleted successfully with id: {}", customer.getId());
    }

    @Override
    public Customer findById(Long id) {
        log.debug("Fetching customer with id: {}", id);
        return customerDao.findById(id)
                .orElseThrow(() -> {
                    log.error("Customer not found with id: {}", id);
                    return new EntityNotFoundException("Customer not found with id: " + id);
                });
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Fetching all customers.");
        List<Customer> customers = customerDao.findAll();
        log.debug("Total customers fetched: {}", customers.size());
        return customers;
    }
}
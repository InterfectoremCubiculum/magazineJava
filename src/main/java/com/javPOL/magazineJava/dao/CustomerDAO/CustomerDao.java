package com.javPOL.magazineJava.dao.CustomerDAO;

import com.javPOL.magazineJava.dao.Dao;
import com.javPOL.magazineJava.model.Customer;
import com.javPOL.magazineJava.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomerDao extends Dao<Customer, Long> {
    List<Customer> findByUser(User user);

    Optional<Customer> findByIdAndUser(Long id, User user);

    List<Customer> findByUserAndIsDefault(User user, Boolean isDefault);

    void clearDefaultForUser(User user);
}

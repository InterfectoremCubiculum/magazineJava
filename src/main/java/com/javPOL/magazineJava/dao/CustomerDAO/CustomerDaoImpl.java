package com.javPOL.magazineJava.dao.CustomerDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends DaoImpl<Customer, Integer> implements CustomerDao {
    public CustomerDaoImpl() {
        super(Customer.class);
    }
}

package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dto.customer.CreateCustomerDto;
import com.javPOL.magazineJava.dto.customer.CustomerResponseDto;
import com.javPOL.magazineJava.dto.customer.UpdateCustomerDto;
import com.javPOL.magazineJava.model.Customer;
import com.javPOL.magazineJava.model.User;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    Customer update(Customer customer);

    void delete(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    CustomerResponseDto createAddress(User user, CreateCustomerDto createCustomerDto);

    CustomerResponseDto updateAddress(Long addressId, UpdateCustomerDto updateCustomerDto, User user);

    void deleteAddress(Long addressId, User user);

    CustomerResponseDto getAddress(Long addressId, User user);

    List<CustomerResponseDto> getMyAddresses(User user);

    CustomerResponseDto setAsDefault(Long addressId, User user);
}
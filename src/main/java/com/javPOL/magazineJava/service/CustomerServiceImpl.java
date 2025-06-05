package com.javPOL.magazineJava.service;

import com.javPOL.magazineJava.dao.CustomerDAO.CustomerDao;
import com.javPOL.magazineJava.dto.customer.CreateCustomerDto;
import com.javPOL.magazineJava.dto.customer.CustomerResponseDto;
import com.javPOL.magazineJava.dto.customer.UpdateCustomerDto;
import com.javPOL.magazineJava.model.Customer;
import com.javPOL.magazineJava.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        try {
            customerDao.save(customer);
            return customer;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save customer address", e);
        }
    }

    @Override
    @Transactional
    public Customer update(Customer customer) {
        try {
            customerDao.update(customer);
            return customer;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update customer address", e);
        }
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        try {
            customerDao.delete(customer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer address", e);
        }
    }

    @Override
    public Customer findById(Long id) {
        return customerDao.findById(id)
                .orElseThrow(() -> {
                    return new EntityNotFoundException("Customer address not found with id: " + id);
                });
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    @Transactional
    public CustomerResponseDto createAddress(User user, CreateCustomerDto createCustomerDto) {
        try {
            validateCreateCustomerDto(createCustomerDto);

            if (createCustomerDto.getIsDefault() == null) {
                createCustomerDto.setIsDefault(false);
            }

            List<Customer> existingAddresses = customerDao.findByUser(user);
            if (existingAddresses.isEmpty()) {
                createCustomerDto.setIsDefault(true);
            } else if (createCustomerDto.getIsDefault()) {
                customerDao.clearDefaultForUser(user);
            }

            Customer customer = new Customer();
            customer.setUser(user);
            customer.setName(createCustomerDto.getName());
            customer.setFirstName(createCustomerDto.getFirstName());
            customer.setLastName(createCustomerDto.getLastName());
            customer.setStreet(createCustomerDto.getStreet());
            customer.setHouseNumber(createCustomerDto.getHouseNumber());
            customer.setApartmentNumber(createCustomerDto.getApartmentNumber());
            customer.setCity(createCustomerDto.getCity());
            customer.setPostalCode(createCustomerDto.getPostalCode());
            customer.setCountry(createCustomerDto.getCountry());
            customer.setPhoneNumber(createCustomerDto.getPhoneNumber());
            customer.setIsDefault(createCustomerDto.getIsDefault());

            Customer savedCustomer = save(customer);
            return toResponseDto(savedCustomer);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create address: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public CustomerResponseDto updateAddress(Long addressId, UpdateCustomerDto updateCustomerDto, User user) {
        try {
            Customer customer = customerDao.findByIdAndUser(addressId, user)
                    .orElseThrow(() -> {
                        return new EntityNotFoundException("Address not found or access denied");
                    });

            if (updateCustomerDto.getName() != null) {
                customer.setName(updateCustomerDto.getName());
            }
            if (updateCustomerDto.getFirstName() != null) {
                customer.setFirstName(updateCustomerDto.getFirstName());
            }
            if (updateCustomerDto.getLastName() != null) {
                customer.setLastName(updateCustomerDto.getLastName());
            }
            if (updateCustomerDto.getStreet() != null) {
                customer.setStreet(updateCustomerDto.getStreet());
            }
            if (updateCustomerDto.getHouseNumber() != null) {
                customer.setHouseNumber(updateCustomerDto.getHouseNumber());
            }
            if (updateCustomerDto.getApartmentNumber() != null) {
                customer.setApartmentNumber(updateCustomerDto.getApartmentNumber());
            }
            if (updateCustomerDto.getCity() != null) {
                customer.setCity(updateCustomerDto.getCity());
            }
            if (updateCustomerDto.getPostalCode() != null) {
                customer.setPostalCode(updateCustomerDto.getPostalCode());
            }
            if (updateCustomerDto.getCountry() != null) {
                customer.setCountry(updateCustomerDto.getCountry());
            }
            if (updateCustomerDto.getPhoneNumber() != null) {
                customer.setPhoneNumber(updateCustomerDto.getPhoneNumber());
            }

            if (updateCustomerDto.getIsDefault() != null && updateCustomerDto.getIsDefault()
                    && !customer.getIsDefault()) {
                customerDao.clearDefaultForUser(user);
                customer.setIsDefault(true);
            }

            Customer updatedCustomer = update(customer);
            return toResponseDto(updatedCustomer);

        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update address: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteAddress(Long addressId, User user) {
        try {
            Customer customer = customerDao.findByIdAndUser(addressId, user)
                    .orElseThrow(() -> {
                        return new EntityNotFoundException("Address not found or access denied");
                    });

            boolean wasDefault = customer.getIsDefault();
            delete(customer);

            if (wasDefault) {
                List<Customer> remainingAddresses = customerDao.findByUser(user);
                if (!remainingAddresses.isEmpty()) {
                    Customer newDefault = remainingAddresses.get(0);
                    newDefault.setIsDefault(true);
                    update(newDefault);
                }
            }

        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete address: " + e.getMessage(), e);
        }
    }

    @Override
    public CustomerResponseDto getAddress(Long addressId, User user) {
        Customer customer = customerDao.findByIdAndUser(addressId, user)
                .orElseThrow(() -> {
                    return new EntityNotFoundException("Address not found or access denied");
                });

        return toResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getMyAddresses(User user) {
        List<Customer> addresses = customerDao.findByUser(user);
        return addresses.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerResponseDto setAsDefault(Long addressId, User user) {
        try {
            Customer customer = customerDao.findByIdAndUser(addressId, user)
                    .orElseThrow(() -> {
                        return new EntityNotFoundException("Address not found or access denied");
                    });

            if (!customer.getIsDefault()) {
                customerDao.clearDefaultForUser(user);
                customer.setIsDefault(true);
                Customer updatedCustomer = update(customer);
                return toResponseDto(updatedCustomer);
            } else {
                return toResponseDto(customer);
            }

        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to set address as default: " + e.getMessage(), e);
        }
    }

    private void validateCreateCustomerDto(CreateCustomerDto dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Address name is required");
        }
        if (dto.getFirstName() == null || dto.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (dto.getLastName() == null || dto.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (dto.getStreet() == null || dto.getStreet().trim().isEmpty()) {
            throw new IllegalArgumentException("Street is required");
        }
        if (dto.getHouseNumber() == null || dto.getHouseNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("House number is required");
        }
        if (dto.getCity() == null || dto.getCity().trim().isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
        if (dto.getPostalCode() == null || dto.getPostalCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code is required");
        }
        if (dto.getCountry() == null || dto.getCountry().trim().isEmpty()) {
            throw new IllegalArgumentException("Country is required");
        }
    }

    private CustomerResponseDto toResponseDto(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setUserId(customer.getUser().getId());
        dto.setName(customer.getName());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setStreet(customer.getStreet());
        dto.setHouseNumber(customer.getHouseNumber());
        dto.setApartmentNumber(customer.getApartmentNumber());
        dto.setCity(customer.getCity());
        dto.setPostalCode(customer.getPostalCode());
        dto.setCountry(customer.getCountry());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setIsDefault(customer.getIsDefault());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        return dto;
    }
}
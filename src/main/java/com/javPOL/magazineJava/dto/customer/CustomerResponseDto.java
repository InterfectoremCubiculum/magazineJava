package com.javPOL.magazineJava.dto.customer;

import com.javPOL.magazineJava.model.Customer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponseDto {

    private Long id;
    private Long userId;
    private String name;
    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private Boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerResponseDto(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        street = customer.getStreet();
        houseNumber = customer.getHouseNumber();
        apartmentNumber = customer.getApartmentNumber();
        city = customer.getCity();
        postalCode = customer.getPostalCode();
        country = customer.getCountry();
        phoneNumber = customer.getPhoneNumber();
        isDefault = customer.getIsDefault();
        createdAt = customer.getCreatedAt();
        updatedAt = customer.getUpdatedAt();
    }
}


package com.javPOL.magazineJava.dto.customer;

import lombok.Data;

@Data
public class UpdateCustomerDto {

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
}
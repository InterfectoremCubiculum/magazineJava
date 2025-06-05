package com.javPOL.magazineJava.dto.customer;

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
}
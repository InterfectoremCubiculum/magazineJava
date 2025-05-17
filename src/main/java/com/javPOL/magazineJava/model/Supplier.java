package com.javPOL.magazineJava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Supplier {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String taxIdentificationNumber;
    private int bankAccountNumber;
    private int phoneNumber;
    private int internationalDialingNumber;
    private String country;
    private String websiteURL;
}

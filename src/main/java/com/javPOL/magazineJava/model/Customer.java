package com.javPOL.magazineJava.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private String emailAddress;
    private int phoneNumber;
    private int iternationalDialingCodes;
    private String postalCode;
    private String city;
    private String localNumber;
    private String companyName;
    private String companyAddress;
    private String taxIdentificationNumber;

}

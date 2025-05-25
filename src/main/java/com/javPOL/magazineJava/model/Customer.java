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
    private Long id;
    private String name;
    private String surname;
    private String emailAddress;
    private Long phoneNumber;
    private Long iternationalDialingCodes;
    private String postalCode;
    private String city;
    private String localNumber;
    @Column(nullable = true)
    private String companyName;
    @Column(nullable = true)
    private String companyAddress;
    @Column(nullable = true)
    private String taxIdentificationNumber;

}

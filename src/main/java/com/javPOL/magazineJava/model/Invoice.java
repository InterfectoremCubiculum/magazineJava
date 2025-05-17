package com.javPOL.magazineJava.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_Id", nullable = false)
    private Customer customer;
    private Date issueDate;
    private Date saleDate;
    private Date paymentDueDate;
    private String paymentMethod;
}

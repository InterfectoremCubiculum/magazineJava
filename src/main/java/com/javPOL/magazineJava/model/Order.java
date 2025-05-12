package com.javPOL.magazineJava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn (name = "customerId", nullable = false)
    public Customer customer;
}

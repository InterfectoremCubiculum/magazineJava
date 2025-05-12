package com.javPOL.magazineJava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "product")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn (name = "categoryId")
    private Category category;

    private String name;
    private double weight;
    private double price;
    private String description;
}

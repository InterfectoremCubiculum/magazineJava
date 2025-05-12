package com.javPOL.magazineJava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

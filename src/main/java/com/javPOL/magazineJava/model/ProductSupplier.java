package com.javPOL.magazineJava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ProductSupplier {

    @EmbeddedId
    private ProductSupplierId id;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Product product;

    private int quantity;
    private double unitValue;

}

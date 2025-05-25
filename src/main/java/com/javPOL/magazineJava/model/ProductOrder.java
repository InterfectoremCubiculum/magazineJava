package com.javPOL.magazineJava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "productOrder")
public class ProductOrder {

    @EmbeddedId
    private ProductOrderId id = new ProductOrderId();

    @ManyToOne
    @MapsId("productId")
    @JoinColumn (name = "product_Id")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn (name = "order_Id")
    @JsonBackReference
    private Order order;

    private int quantity;
    private double unityValue;
}

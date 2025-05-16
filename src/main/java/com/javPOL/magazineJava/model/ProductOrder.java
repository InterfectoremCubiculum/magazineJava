package com.javPOL.magazineJava.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "productOrder")
public class ProductOrder {

    @EmbeddedId
    private ProductOrderId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn (name = "product_Id")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn (name = "order_Id")
    private Order order;

    private int quantity;
    private double unityValue;
}

package com.javPOL.magazineJava.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductOrderId implements Serializable {
    private int productId;
    private int orderId;
}
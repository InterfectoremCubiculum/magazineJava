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
    private Long productId;
    private Long orderId;
}
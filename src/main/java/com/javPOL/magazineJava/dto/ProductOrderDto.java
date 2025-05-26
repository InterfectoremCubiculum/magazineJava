package com.javPOL.magazineJava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {
    private Long productId;
    private int quantity;
    private double unitPrice;
}

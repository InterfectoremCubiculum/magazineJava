package com.javPOL.magazineJava.dto;

import com.javPOL.magazineJava.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderHistoryDto {
    private String name;
    private double weight;
    private int quantity;
    private double unitPrice;

    public ProductOrderHistoryDto (Product p, double unitPrice, int quantity) {
        this.name = p.getName();
        this.weight = p.getWeight();
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}

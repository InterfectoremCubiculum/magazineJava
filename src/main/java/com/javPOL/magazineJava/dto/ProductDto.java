package com.javPOL.magazineJava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private double weight;
    private double price;
    private String description;
    private Long categoryId;
}
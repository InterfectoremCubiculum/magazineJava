package com.javPOL.magazineJava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDto {
    private Long customerId;
    private List<ProductOrderDto> products;
}

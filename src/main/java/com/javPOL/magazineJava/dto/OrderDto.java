package com.javPOL.magazineJava.dto;

import com.javPOL.magazineJava.dto.customer.CustomerResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private CustomerResponseDto customerDto;
    private List<ProductOrderHistoryDto> productOrderHistoryDto;
}

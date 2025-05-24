package com.javPOL.magazineJava.dto;

import com.javPOL.magazineJava.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeOrderDto {
    Customer customer;
    ProductOrderDto products;
}

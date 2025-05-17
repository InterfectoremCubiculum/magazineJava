package com.javPOL.magazineJava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class WarehouseProduct {

    @EmbeddedId
    private WarehouseProductId id;

    @ManyToOne
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_Id")
    private Warehouse warehouse;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_Id")
    private Product product;

    private int quantity;
}

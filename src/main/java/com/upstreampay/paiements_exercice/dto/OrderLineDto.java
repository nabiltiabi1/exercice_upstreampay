package com.upstreampay.paiements_exercice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {

    private String productName;
    private int quantity;
    private BigDecimal price;
}

package com.plucas.ws.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter @Setter
public class ProductRequestDTO {

    private String title;
    private BigDecimal price;
    private Integer quantity;
}

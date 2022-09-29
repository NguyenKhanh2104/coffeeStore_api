package com.example.intern.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CartDTO {
    Long id;
    String nameProduct;
    double priceProduct;
    double price;
    Integer qty;

}

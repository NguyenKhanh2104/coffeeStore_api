package com.example.api_coffeestore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CartDTO {
    Long id;
    Long user_id;
    String nameProduct;
    double priceProduct;
    double price;
    Integer qty;

}

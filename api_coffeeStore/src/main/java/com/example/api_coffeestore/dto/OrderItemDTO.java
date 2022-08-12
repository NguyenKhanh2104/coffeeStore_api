package com.example.api_coffeestore.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    Long id;
    Integer quantity;
    String productName;
    double productPrice;
    Long order_id;
    double price;

}

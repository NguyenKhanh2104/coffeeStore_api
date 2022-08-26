package com.example.api_coffeestore.dto;

import com.example.api_coffeestore.model.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    String id;
    Integer quantity;
    String fullName;
    double totalPrice;
    Long userId;
    String dateCreate;
    String note;
    String payment_type;
}

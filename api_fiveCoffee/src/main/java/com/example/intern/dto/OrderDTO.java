package com.example.intern.dto;

import lombok.Data;

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

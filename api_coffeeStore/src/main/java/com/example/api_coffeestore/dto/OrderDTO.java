package com.example.api_coffeestore.dto;

import com.example.api_coffeestore.model.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    String id;
//    List<OrderItemDTO> orderItem;
    Long user;
    double totalPrice;
    Date dateCreate;
    String note;
    String payment_type;
}

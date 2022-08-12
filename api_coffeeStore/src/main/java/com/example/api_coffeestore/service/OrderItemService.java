package com.example.api_coffeestore.service;

import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.model.OrderItem;
import com.example.api_coffeestore.repository.OrderItemRepo;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> saveOrderItem(List<OrderItem> checkout_item);
    OrderItem save(OrderItem checkout_item);
    List<OrderItem> list();
//    List<OrderItem> getByOrderId(String checkoutId);
    List<OrderItem> getOrderId(Long id);
    List<OrderItem> getProductId(Long id);
}

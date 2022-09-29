package com.example.intern.services;

import com.example.intern.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderItemService {
    List<OrderItem> saveOrderItem(List<OrderItem> checkout_item);
    OrderItem save(OrderItem checkout_item);
    OrderItem getOrderId(String id);
    List<OrderItem> getProductId(String id);
    List<OrderItem> findAll();
    void removeByOrderId(String id);
    List<OrderItem> getOrderItemByOrderId(String id);
}

package com.example.api_coffeestore.service;

import com.example.api_coffeestore.model.Order;

import java.util.List;

public interface OrderService {
    Boolean checkTotalAmountAgainstCart(double totalAmount, Long userId);
    Order saveProductsForCheckout(Order tmp) throws Exception;
    List<Order> getAllCheckoutByUserId(Long userId);
    List<Order> findAll();
    List<Order> getLast();
    List<Order> getCheckoutsByUserId(Long user_id);
    Order getById(String id) throws Exception;

}

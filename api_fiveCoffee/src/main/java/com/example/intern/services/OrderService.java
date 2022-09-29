package com.example.intern.services;


import com.example.intern.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    Boolean checkTotalAmountAgainstCart(double totalAmount, Long userId);
    Order saveProductsForCheckout(Order tmp) throws Exception;
    List<Order> getAllCheckoutByUserId(Long userId);
    List<Order> findAll();
    List<Order> getLast();
    List<Order> getCheckoutsByUserId(Long user_id);
    Order getById(String id) throws Exception;

    Order updateOrder(String id, Order o) throws Exception;

    void remove(String id);
}

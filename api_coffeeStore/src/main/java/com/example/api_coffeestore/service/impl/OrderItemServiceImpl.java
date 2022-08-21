package com.example.api_coffeestore.service.impl;

import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.model.OrderItem;
import com.example.api_coffeestore.repository.OrderItemRepo;
import com.example.api_coffeestore.service.CartService;
import com.example.api_coffeestore.service.OrderItemService;
import com.example.api_coffeestore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepo orderItemRepo;
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Override
    public List<OrderItem> saveOrderItem(List<OrderItem> item) {
        for (int i = 0; i < item.size(); i++) {
            orderItemRepo.save(item.get(i));
            System.err.println("phan tu thu ");
        }
        return item;
    }

    @Override
    public OrderItem save(OrderItem item) {
        return orderItemRepo.save(item);
    }

    @Override
    public List<OrderItem> list() {
        return orderItemRepo.findAll();
    }

//    @Override
//    public List<OrderItem> getByOrderId(String id) {
//        List<OrderItem> list = orderItemRepo.getByOrderId(id);
//        List<OrderItem> newList = new ArrayList<>();
//        for (OrderItem checkoutItem : list
//        ) {
//            newList.add(checkoutItem);
//        }
//        return newList;
//    }

    @Override
    public OrderItem getOrderId(String id) {
        OrderItem item = orderItemRepo.getByOrderId(id);
        return item;
    }

    @Override
    public List<OrderItem> getProductId(String id) {
        List<OrderItem> listItem = orderItemRepo.findAll();
        List<OrderItem> rs = new ArrayList<>();
        for (OrderItem c : listItem
        ) {
            if (c.getOrder().getId() == id) {
                rs.add(c);
            }
        }
        return rs;
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepo.findAll();
    }
}

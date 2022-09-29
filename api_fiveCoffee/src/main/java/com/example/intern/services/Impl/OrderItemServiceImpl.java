package com.example.intern.services.Impl;
import com.example.intern.model.OrderItem;
import com.example.intern.repository.OrderItemRepo;
import com.example.intern.services.CartService;
import com.example.intern.services.OrderItemService;
import com.example.intern.services.OrderService;
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

    @Override
    public void removeByOrderId(String id) {
        List<OrderItem> list = this.getOrderItemByOrderId(id);
        for (OrderItem i:list
             ) {
            orderItemRepo.delete(i);
        }

    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(String id) {
        List<OrderItem> list = this.findAll();
        List<OrderItem> rs = new ArrayList<>();
        for (OrderItem item:list
             ) {
            if(item.getOrder().getId().equals(id)){
                rs.add(item);
            }
        }
        return rs;
    }
}

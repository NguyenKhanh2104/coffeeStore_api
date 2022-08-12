package com.example.api_coffeestore.service.impl;

import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.model.User;
import com.example.api_coffeestore.repository.CartRepo;
import com.example.api_coffeestore.repository.OrderRepo;
import com.example.api_coffeestore.service.OrderService;
import com.example.api_coffeestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    public final OrderRepo orderRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserService userService;
    @Override
    public Boolean checkTotalAmountAgainstCart(double totalAmount, Long userId) {
        double total_amount = cartRepo.getTotalAmountByUserId(userId);
        if (total_amount == totalAmount) {
            return true;
        }
        System.err.print("Error from request " + total_amount + " --db-- " + totalAmount);
        return false;
    }

    @Override
    public Order saveProductsForCheckout(Order tmp) throws Exception {
        return orderRepo.saveAndFlush(tmp);
    }

    @Override
    public List<Order> getAllCheckoutByUserId(Long userId) {
        List<User> list = userService.findAll();
        List<Order> list2 = orderRepo.findAll();
        List<Order> rs = new ArrayList<>();
        for (User u : list
        ) {
            for (Order c : list2
            ) {
                if(u.getId()==userId){
                    c.getUser().equals(u);
                    rs.add(c);
                }
            }
        }
        return rs;
    }

    @Override
    public List<Order> listCheckout() {
        return orderRepo.findAll();
    }
    @Override
    public Order getById(Long id)throws Exception {
        return this.orderRepo.findById(id).orElseThrow(() ->new Exception("Order is not found"));
    }
}

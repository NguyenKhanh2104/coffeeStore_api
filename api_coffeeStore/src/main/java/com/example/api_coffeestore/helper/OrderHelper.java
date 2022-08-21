package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.OrderDTO;
import com.example.api_coffeestore.mapper.OrderMapper;
import com.example.api_coffeestore.model.Cart;
import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.payload.response.ApiResponse;
import com.example.api_coffeestore.security.ShoppingConfiguration;
import com.example.api_coffeestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderHelper {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper orderMapper;

    public List<OrderDTO> getLast(){
        List<Order> listOrder = orderService.findAll();
        List<OrderDTO> dto = new ArrayList<>();
            Order order = listOrder.get(listOrder.size()-1);
            OrderDTO orderDto = orderMapper.toDto(order);
            dto.add(orderDto);

        return dto;
    }

    public OrderDTO findById(String id) throws Exception {
        Order o = orderService.getById(id);
        OrderDTO dto  = orderMapper.toDto(o);
        return dto;
    }

    public ResponseEntity<?> getCheckoutsByUserId(HashMap<String, String> getCheckoutRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCheckoutRequest)) {
            }
            List<Order> obj = orderService.getCheckoutsByUserId(Long.parseLong(getCheckoutRequest.get("userId")));
            List<OrderDTO> rs = new ArrayList<>();
            for (Order o:obj
            ) {
                OrderDTO dto = orderMapper.toDto(o);
                rs.add(dto);
            }
            return ResponseEntity.ok(rs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
}

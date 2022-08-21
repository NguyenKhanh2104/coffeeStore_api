package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.OrderItemDTO;
import com.example.api_coffeestore.model.Cart;
import com.example.api_coffeestore.model.OrderItem;
import com.example.api_coffeestore.service.ProductService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    public OrderItemDTO toDto(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setOrder_id(item.getOrder().getId());
        dto.setPrice(item.getPrice());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQty());
        dto.setProductPrice(item.getProduct().getPrice());
        return dto;
    }
}

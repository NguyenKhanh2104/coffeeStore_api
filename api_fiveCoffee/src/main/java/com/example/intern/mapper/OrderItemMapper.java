package com.example.intern.mapper;

import com.example.intern.dto.OrderItemDTO;
import com.example.intern.model.OrderItem;
import com.example.intern.services.ProductService;
import com.example.intern.services.UserService;
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

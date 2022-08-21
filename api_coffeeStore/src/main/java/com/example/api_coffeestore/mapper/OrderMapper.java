package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.OrderDTO;
import com.example.api_coffeestore.dto.OrderItemDTO;
import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.model.OrderItem;
import com.example.api_coffeestore.service.OrderItemService;
import com.example.api_coffeestore.service.OrderService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
@Autowired
OrderItemService orderItemService;
@Autowired
OrderItemMapper orderItemMapper;
    public OrderDTO toDto(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setNote(order.getNote());
        dto.setPayment_type(order.getPayment_type());
        dto.setDateCreate(order.getDateCreate());
        dto.setUser(order.getUser().getId());
        dto.setTotalPrice(order.getTotalPrice());

//        List<OrderItemDTO> itemDto = new ArrayList<>();
//        for (OrderItem o: item
//             ) {
//            OrderItemDTO orderItemDto = orderItemMapper.toDto(o);
//            itemDto.add(orderItemDto);
//        }
//        dto.setOrderItem(itemDto);
        return dto;

    }
}

package com.example.intern.mapper;

import com.example.intern.dto.OrderDTO;
import com.example.intern.model.Order;
import com.example.intern.model.OrderItem;
import com.example.intern.services.OrderItemService;
import com.example.intern.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
@Autowired
OrderItemService orderItemService;
@Autowired
OrderItemMapper orderItemMapper;
@Autowired
UserService userService;
    public OrderDTO toDto(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setNote(order.getNote());
        dto.setPayment_type(order.getPayment_type());
        dto.setDateCreate(order.getDateCreate());
        dto.setUserId(order.getUser().getId());
        dto.setFullName(order.getUser().getFullName());

        dto.setTotalPrice(order.getTotalPrice());
        int count = 0;
        List<OrderItem> list = orderItemService.findAll();
        for (OrderItem item:list
             ) {
            if(item.getOrder().getId()==order.getId()){
                count+= item.getQty();
                dto.setQuantity(count);
            }
        }
        return dto;

    }

    public Order toEntity(OrderDTO orderDetail) throws Exception {
        Order order = new Order();
        order.setId(orderDetail.getId());
        order.setDateCreate(orderDetail.getDateCreate());
        order.setTotalPrice(orderDetail.getTotalPrice());
        order.setNote(orderDetail.getNote());
        order.setPayment_type(orderDetail.getPayment_type());
        return order;
    }
}

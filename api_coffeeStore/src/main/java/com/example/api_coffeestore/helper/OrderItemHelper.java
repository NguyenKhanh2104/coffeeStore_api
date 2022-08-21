package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.dto.OrderDTO;
import com.example.api_coffeestore.dto.OrderItemDTO;
import com.example.api_coffeestore.mapper.OrderItemMapper;
import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.model.OrderItem;
import com.example.api_coffeestore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemHelper {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderHelper orderHelper;

    public List<OrderItemDTO> getByOrderId() {
        List<OrderItem> list = orderItemService.findAll();
        List<OrderItemDTO> rs = new ArrayList<>();
        List<OrderDTO> listOrder = orderHelper.getLast();
        for (OrderItem o : list
        ) {
            for (OrderDTO orderDto : listOrder
            ) {
                if (orderDto.getId() == o.getOrder().getId()) {
                    OrderItemDTO dto = orderItemMapper.toDto(o);
                    rs.add(dto);
                }
            }
        }
        return rs;
    }

}

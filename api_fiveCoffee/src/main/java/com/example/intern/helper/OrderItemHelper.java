package com.example.intern.helper;

import com.example.intern.dto.OrderDTO;
import com.example.intern.dto.OrderItemDTO;
import com.example.intern.mapper.OrderItemMapper;
import com.example.intern.model.OrderItem;
import com.example.intern.services.OrderItemService;
import com.example.intern.services.OrderService;
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

    public List<OrderItemDTO> getOrderItemByOrderId(String id) {
        List<OrderItemDTO> rs = new ArrayList<>();
        List<OrderItem> list = orderItemService.getOrderItemByOrderId(id);
        for (OrderItem item : list
        ) {
            OrderItemDTO dto = orderItemMapper.toDto(item);
            rs.add(dto);

        }
        return rs;
    }
}

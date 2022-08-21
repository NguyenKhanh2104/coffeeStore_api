package com.example.api_coffeestore.repository;

import com.example.api_coffeestore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
    @Query("Select item  FROM OrderItem item WHERE item.order.id=:order")
    OrderItem getByOrderId(@Param("order")String order);
    @Query("Select item FROM OrderItem  item WHERE item.order.user.id=:user_id")
    List<com.example.api_coffeestore.model.OrderItem> getByUserId(@Param("user_id")Long user_id);
}

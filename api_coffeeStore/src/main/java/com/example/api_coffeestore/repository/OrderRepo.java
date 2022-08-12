package com.example.api_coffeestore.repository;

import com.example.api_coffeestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query("Select checkCart  FROM Order checkCart WHERE checkCart.user.id=:user_id")
    List<Order> getByUserId(@Param("user_id")Long user_id);
}

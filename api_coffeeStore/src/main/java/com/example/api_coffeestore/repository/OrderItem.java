package com.example.api_coffeestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItem extends JpaRepository<com.example.api_coffeestore.model.OrderItem,Long> {
}

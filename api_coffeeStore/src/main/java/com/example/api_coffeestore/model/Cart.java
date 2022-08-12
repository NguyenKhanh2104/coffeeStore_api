package com.example.api_coffeestore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer qty;
    private double price;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user;
}

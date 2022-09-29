package com.example.intern.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer qty;
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;
    private double price;
}

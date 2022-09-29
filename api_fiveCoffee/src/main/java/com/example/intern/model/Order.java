package com.example.intern.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Order_t")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;
    private String payment_type;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    User user;

    private double totalPrice;
    private String dateCreate;
    private String note;
}

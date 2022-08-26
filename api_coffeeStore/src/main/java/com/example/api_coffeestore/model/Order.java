package com.example.api_coffeestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.transaction.jta.platform.internal.JOnASJtaPlatform;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
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

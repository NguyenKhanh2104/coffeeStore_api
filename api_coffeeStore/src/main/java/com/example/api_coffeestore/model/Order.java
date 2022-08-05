package com.example.api_coffeestore.model;

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
    private Long id;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderitem;
    private String payment_type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    private double totalPrice;
    @DateTimeFormat(fallbackPatterns = "dd/MM/yyyy")
    private Date dateCreate;
    private String note;
}

package com.example.api_coffeestore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private double price;
    private String description;
    private String img;
    private String qty;
    private LocalDate dateCreate;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
}

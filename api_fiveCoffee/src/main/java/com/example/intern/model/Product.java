package com.example.intern.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;
    private String description;
    private LocalDate dateCreate;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageProduct")
    private FileDB imageProduct;
}

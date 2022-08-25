package com.example.api_coffeestore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    private String qty;
    private LocalDate dateCreate;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageProduct")
    private FileDB imageProduct;
}

package com.example.api_coffeestore.dto;

import com.example.api_coffeestore.model.Category;
import com.example.api_coffeestore.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String img;
    private String qty;
    private LocalDate dateCreate;
    private Integer category;

}

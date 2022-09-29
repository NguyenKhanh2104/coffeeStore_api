package com.example.intern.dto;
import com.example.intern.message.ResponseFile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String description;

//    private String qty;
    private LocalDate dateCreate;
    private String category;
    private ResponseFile imageProduct;
}

package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.model.Category;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    @Autowired
    CategoryService categoryService;

    public ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCategory(product.getCategory().getId());
        dto.setDateCreate(product.getDateCreate());
        dto.setDescription(product.getDescription());
        dto.setImg(product.getImg());
        dto.setPrice(product.getPrice());
        dto.setName(product.getName());
        dto.setQty(product.getQty());
        return dto;
    }

    public Product toEntity(ProductDTO dto) throws Exception {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setQty(dto.getQty());
        entity.setDescription(dto.getDescription());
        Category cate = categoryService.findById(dto.getCategory());
        entity.setCategory(cate);
        entity.setImg(dto.getImg());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDateCreate(dto.getDateCreate());
        return entity;
    }
}

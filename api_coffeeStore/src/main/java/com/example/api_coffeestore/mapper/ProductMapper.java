package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.FileDTO;
import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.message.ResponseFile;
import com.example.api_coffeestore.model.Category;
import com.example.api_coffeestore.model.FileDB;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.service.CategoryService;
import com.example.api_coffeestore.service.FileStorageService;
import com.example.api_coffeestore.service.impl.FileStorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    @Autowired
    CategoryService categoryService;
    @Autowired
    FileStorageService storageService;
@Autowired
FileMapper fileMapper;
    public ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCategory(product.getCategory().getId());
        dto.setDateCreate(product.getDateCreate());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setName(product.getName());
        dto.setQty(product.getQty());
        FileDB fileDB = storageService.getFile(product.getImageProduct().getId());
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(fileDB.getId())
                .toUriString();
        ResponseFile resp = new ResponseFile(fileDB.getName(),fileDownloadUri,fileDB.getType(),fileDB.getData().length);
        dto.setImageProduct(resp);
        return dto;
    }

    public Product toEntity(ProductDTO dto) throws Exception {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setQty(dto.getQty());
        entity.setDescription(dto.getDescription());
        Category cate = categoryService.findById(dto.getCategory());
        entity.setCategory(cate);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDateCreate(dto.getDateCreate());
        return entity;
    }
}

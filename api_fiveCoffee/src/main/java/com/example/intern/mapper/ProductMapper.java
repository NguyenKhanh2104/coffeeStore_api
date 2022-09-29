package com.example.intern.mapper;

import com.example.intern.dto.ProductDTO;
import com.example.intern.message.ResponseFile;
import com.example.intern.model.Category;
import com.example.intern.model.FileDB;
import com.example.intern.model.Product;
import com.example.intern.services.CategoryService;
import com.example.intern.services.FileStorageService;
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
        dto.setCategory(product.getCategory().getName());
        dto.setDateCreate(product.getDateCreate());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setName(product.getName());
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
        entity.setDescription(dto.getDescription());
        Category cate = categoryService.findByName(dto.getCategory());
        entity.setCategory(cate);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDateCreate(dto.getDateCreate());
        return entity;
    }
}

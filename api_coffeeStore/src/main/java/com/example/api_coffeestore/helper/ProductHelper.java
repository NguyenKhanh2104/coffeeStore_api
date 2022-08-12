package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.mapper.ProductMapper;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductHelper {
    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;

    public List<ProductDTO> getAll() {
        List<Product> list = productService.findAll();
        List<ProductDTO> rs = new ArrayList<>();
        for (Product p : list
        ) {
            ProductDTO dto = productMapper.toDto(p);
            rs.add(dto);
        }
        return rs;
    }
    public  ProductDTO findById(Long id) throws Exception {
        Product product = productService.findById(id);
        ProductDTO dto = productMapper.toDto(product);

        return dto;
    }
    public List<ProductDTO> findByCategory(Integer id){
        List<Product> list = productService.findByCategory(id);
        List<ProductDTO> rs = new ArrayList<>();
        for (Product p:list
             ) {
            ProductDTO dto = productMapper.toDto(p);
            rs.add(dto);
        }
        return rs;
    }

}
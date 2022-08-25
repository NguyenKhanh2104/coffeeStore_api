package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.dto.OrderDTO;
import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.mapper.ProductMapper;
import com.example.api_coffeestore.message.ResponseFile;
import com.example.api_coffeestore.model.FileDB;
import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.service.FileStorageService;
import com.example.api_coffeestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductHelper {
    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    FileStorageService fileStorageService;

    public ResponseEntity<?> getAll() {

        List<Product> list = productService.findAll();
        List<ProductDTO> rs = new ArrayList<>();
        for (Product p : list
        ) {
            ProductDTO dto = productMapper.toDto(p);

            rs.add(dto);
//            return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dto.getImageProduct().getName() + "\"")
//        .body(dto.getImageProduct().getData());

        }
        return ResponseEntity.ok(rs);
    }

    public ProductDTO findById(Long id) throws Exception {
        Product product = productService.findById(id);
        ProductDTO dto = productMapper.toDto(product);

        return dto;
    }

    public Product updateProduct(Long id, ProductDTO productDetail) throws Exception {
        Product p = productMapper.toEntity(productDetail);
        return productService.updateProduct(id, p);


    }

    public List<ProductDTO> findByCategory(Integer id) {
        List<Product> list = productService.findByCategory(id);
        List<ProductDTO> rs = new ArrayList<>();
        for (Product p : list
        ) {
            ProductDTO dto = productMapper.toDto(p);
            rs.add(dto);
        }
        return rs;
    }

    public Product createProduct(ProductDTO productDto) throws Exception {
        LocalDate myObj = LocalDate.now();
        productDto.setDateCreate(myObj);
        Product p = productMapper.toEntity(productDto);
        return productService.create(p);
    }

    public void removeProduct(Long id) {
        productService.remove(id);
    }

}
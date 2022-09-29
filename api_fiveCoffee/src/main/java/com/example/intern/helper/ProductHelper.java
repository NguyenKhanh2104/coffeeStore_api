package com.example.intern.helper;
import com.example.intern.dto.ProductDTO;
import com.example.intern.mapper.ProductMapper;
import com.example.intern.model.FileDB;
import com.example.intern.model.Product;
import com.example.intern.services.FileStorageService;
import com.example.intern.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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
        Stream<FileDB> list = fileStorageService.getAllFiles();
        p.setImageProduct(list.sorted(Comparator.comparing(FileDB::getDateCreate).reversed()).findFirst().get());
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
        Stream<FileDB> list = fileStorageService.getAllFiles();
        p.setImageProduct(list.sorted(Comparator.comparing(FileDB::getDateCreate).reversed()).findFirst().get());
        return productService.create(p);
    }

    public void removeProduct(Long id) {
        productService.remove(id);
    }

}
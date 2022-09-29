package com.example.intern.services;


import com.example.intern.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> findAll();

    Product findById(Long id) throws Exception;

    Product findByName(String name);

    List<Product> findByCategory(Integer id);

    Product updateProduct(Long id, Product productDetail) throws Exception;

    Product create(Product product);

    void remove(Long id);
}

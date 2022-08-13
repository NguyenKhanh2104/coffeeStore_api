package com.example.api_coffeestore.service;

import com.example.api_coffeestore.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
   Product findById(Long id)throws Exception ;
   Product findByName(String name) ;
   List<Product> findByCategory(Integer id);
    Product updateProduct(Long   id, Product productDetail) throws Exception;
    Product create(Product product);
    void remove(Long id);
}

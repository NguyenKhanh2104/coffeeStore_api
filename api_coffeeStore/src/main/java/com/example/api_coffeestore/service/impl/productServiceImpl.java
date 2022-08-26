package com.example.api_coffeestore.service.impl;

import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.repository.ProductRepo;
import com.example.api_coffeestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class productServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) throws Exception {
        return productRepo.findById(id).orElseThrow(() -> new Exception("Product is not found"));
    }

    @Override
    public Product findByName(String name) {
        return productRepo.findProductByName(name);
    }

    @Override
    public List<Product> findByCategory(Integer id) {
        List<Product> list = this.findAll();
        List<Product> rs = new ArrayList<>();
        for (Product p: list
             ) {
            if(p.getCategory().getId()==id){
                rs.add(p);
            }
        }
        return rs;
    }

    @Override
    public Product updateProduct(Long id, Product productDetail) throws Exception {
        Product product = productRepo.findById(id).orElseThrow(() -> new Exception("Product is not found"));
        product.setName(productDetail.getName());
//        product.setQty(productDetail.getQty());
        product.setCategory(productDetail.getCategory());
        product.setDescription(productDetail.getDescription());
        product.setPrice(productDetail.getPrice());
        product.setImageProduct(productDetail.getImageProduct());
        return productRepo.save(product);
    }

    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepo.deleteById(id);
    }
}

package com.example.api_coffeestore.repository;

import com.example.api_coffeestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

        @Query(value = "SELECT p FROM Product p WHERE p.name IN :name")
        Product findProductByName(@Param("name") String name);

    @Query(value = "SELECT p FROM Product p WHERE p.category IN :category")
    List<Product> findProductByCategory(@Param("category") Integer category);
}

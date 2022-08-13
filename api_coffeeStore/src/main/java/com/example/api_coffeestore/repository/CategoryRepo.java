package com.example.api_coffeestore.repository;

import com.example.api_coffeestore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    @Query(value = "SELECT b FROM Category b WHERE b.name IN :name")
    Category findCategoryByName(@Param("name") String name);
}

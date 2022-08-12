package com.example.api_coffeestore.service;

import com.example.api_coffeestore.model.Category;

import java.util.List;

public interface CategoryService {
    Category findByName(String name);
    List<Category> findAll ();
}

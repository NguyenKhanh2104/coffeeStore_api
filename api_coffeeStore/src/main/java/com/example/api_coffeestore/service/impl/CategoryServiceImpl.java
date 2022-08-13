package com.example.api_coffeestore.service.impl;

import com.example.api_coffeestore.model.Category;
import com.example.api_coffeestore.repository.CategoryRepo;
import com.example.api_coffeestore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    @Override
    public Category findByName(String name) {
        return categoryRepo.findCategoryByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Integer id) throws Exception {
        return categoryRepo.findById(id).orElseThrow(() -> new Exception("Category is not found"));
    }
}

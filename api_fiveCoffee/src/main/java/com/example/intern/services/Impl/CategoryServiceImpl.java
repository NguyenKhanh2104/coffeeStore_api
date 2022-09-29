package com.example.intern.services.Impl;

import com.example.intern.model.Category;
import com.example.intern.repository.CategoryRepo;
import com.example.intern.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
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

package com.example.intern.services;

import com.example.intern.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    Category findByName(String name);
    List<Category> findAll ();
    Category findById(Integer id) throws Exception;
}

package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.model.Category;
import com.example.api_coffeestore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Categoryhelper {
    @Autowired
    CategoryService categoryService;
    public List<Category> findAll(){
        List<Category> rs = categoryService.findAll();
        return rs;
    }
}

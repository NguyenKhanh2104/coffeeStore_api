package com.example.intern.helper;
import com.example.intern.model.Category;
import com.example.intern.services.CategoryService;
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

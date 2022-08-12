package com.example.api_coffeestore.service;

import com.example.api_coffeestore.model.User;

import java.util.List;

public interface UserService {
    User findById(Long userId) throws Exception;
    List<User> findAll();
}

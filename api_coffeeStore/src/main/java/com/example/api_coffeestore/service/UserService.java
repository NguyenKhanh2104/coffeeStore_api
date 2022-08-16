package com.example.api_coffeestore.service;

import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.model.User;

import java.util.List;

public interface UserService {
    User findById(Long userId) throws Exception;
    List<User> findAll();
    User updateUser(Long   id, User user) throws Exception;

    void remove(Long id);

    User create(User u);
}

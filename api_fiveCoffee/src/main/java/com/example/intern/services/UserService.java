package com.example.intern.services;

import com.example.intern.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    User findById(Long userId) throws Exception;
    List<User> findAll();
    User updateUser(Long   id, User user) throws Exception;
    User findByFullName(String fullName);
    void remove(Long id);

    User create(User u);
}

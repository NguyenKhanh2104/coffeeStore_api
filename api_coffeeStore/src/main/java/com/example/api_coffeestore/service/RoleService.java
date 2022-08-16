package com.example.api_coffeestore.service;

import com.example.api_coffeestore.model.ERole;
import com.example.api_coffeestore.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findByName(ERole name);
    List<Role> findAll();
}

package com.example.api_coffeestore.service.impl;

import com.example.api_coffeestore.model.ERole;
import com.example.api_coffeestore.model.Role;
import com.example.api_coffeestore.repository.RoleRepo;
import com.example.api_coffeestore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    public final RoleRepo roleRepo;
    @Override
    public Role findByName(ERole name){
        return roleRepo.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }
}

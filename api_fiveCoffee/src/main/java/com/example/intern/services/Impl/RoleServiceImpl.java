package com.example.intern.services.Impl;
import com.example.intern.model.ERole;
import com.example.intern.model.Role;
import com.example.intern.repository.RoleRepository;
import com.example.intern.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepo;
    @Override
    public Role findByName(ERole name){
        return roleRepo.findByName(name).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }
}

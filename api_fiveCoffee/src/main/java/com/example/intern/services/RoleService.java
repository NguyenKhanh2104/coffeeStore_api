package com.example.intern.services;
import com.example.intern.model.ERole;
import com.example.intern.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    Role findByName(ERole name);
    List<Role> findAll();
}

package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.RoleDTO;
import com.example.api_coffeestore.model.ERole;
import com.example.api_coffeestore.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    public  RoleDTO toRoleDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
    public  Role toRoleEntity(RoleDTO roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}

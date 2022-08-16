package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.dto.RoleDTO;
import com.example.api_coffeestore.dto.UserDTO;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.model.Role;
import com.example.api_coffeestore.model.User;
import com.example.api_coffeestore.payload.request.SignupRequest;
import com.example.api_coffeestore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleService roleService;

    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setAddress(user.getAddress());
        dto.setBirthday(user.getBirthday());
        dto.setEmail(user.getEmail());
        dto.setImg(user.getImg());
        dto.setPhone(user.getPhone());
        dto.setSex(user.getSex());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        List<Role> r = user.getRoles();
        for (Role role : r
        ) {
            RoleDTO roleDto = roleMapper.toRoleDTO(role);
            dto.setRole(roleDto.getName());
        }

        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setAddress(dto.getAddress());
        user.setBirthday(dto.getBirthday());
        user.setEmail(dto.getEmail());
        user.setImg(dto.getImg());
        user.setPhone(dto.getPhone());
        user.setSex(dto.getSex());
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        Role r = roleService.findByName(dto.getRole());
        List<Role> list = new ArrayList<>();
        list.add(r);
        user.setRoles(list);
        return user;
    }
//    public User toResponse(UserDTO dto) {
//        User user = new User();
//        user.setAddress(dto.getAddress());
//        user.setBirthday(dto.getBirthday());
//        user.setEmail(dto.getEmail());
//        user.setImg(dto.getImg());
//        user.setPhone(dto.getPhone());
//        user.setSex(dto.getSex());
//        user.setUsername(dto.getUsername());
//        user.setFullName(dto.getFullName());
//        Role r = roleService.findByName(dto.getRole());
//        Set<Role> list = new HashSet<>();
//        list.add(r);
//        user.setRoles(list);
//        return user;
//    }
}

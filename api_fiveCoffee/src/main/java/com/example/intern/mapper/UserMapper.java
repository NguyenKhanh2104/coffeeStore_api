package com.example.intern.mapper;

import com.example.intern.dto.RoleDTO;
import com.example.intern.dto.UserDTO;
import com.example.intern.message.ResponseFile;
import com.example.intern.model.FileDB;
import com.example.intern.model.Role;
import com.example.intern.model.User;
import com.example.intern.services.FileStorageService;
import com.example.intern.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserMapper {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    FileStorageService fileStorageService;
    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setAddress(user.getAddress());
        dto.setBirthday(user.getBirthday());
        dto.setEmail(user.getEmail());
        FileDB fileDB = fileStorageService.getFile(user.getImageUser().getId());
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(fileDB.getId())
                .toUriString();
        ResponseFile resp = new ResponseFile(fileDB.getName(),fileDownloadUri,fileDB.getType(),fileDB.getData().length);
        dto.setImageUser(resp);
        dto.setPhone(user.getPhone());
        dto.setSex(user.getSex());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        Set<Role> r = user.getRoles();
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
        user.setPhone(dto.getPhone());
        user.setSex(dto.getSex());
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        Role role = roleService.findByName(dto.getRole());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
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

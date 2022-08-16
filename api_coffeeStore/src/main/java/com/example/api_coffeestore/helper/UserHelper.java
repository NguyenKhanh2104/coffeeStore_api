package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.dto.UserDTO;
import com.example.api_coffeestore.mapper.UserMapper;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.model.User;
import com.example.api_coffeestore.service.ProductService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserHelper {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    public List<UserDTO> getAll() {
        List<User> list = userService.findAll();
        List<UserDTO> rs = new ArrayList<>();
        for (User u : list
        ) {
            UserDTO dto = userMapper.toDto(u);
            rs.add(dto);
        }
        return rs;
    }

    public User updateUser(Long id, UserDTO userDto) throws Exception {
        User user = userMapper.toEntity(userDto);
        return userService.updateUser(id,user);
    }

    public void removeUser(Long id) {
        userService.remove(id);
    }

    public User createUser(UserDTO userDto) {
        User u = userMapper.toEntity(userDto);
        return userService.create(u);
    }
}

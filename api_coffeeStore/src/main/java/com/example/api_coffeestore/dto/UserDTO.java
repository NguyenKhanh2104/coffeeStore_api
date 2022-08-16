package com.example.api_coffeestore.dto;

import com.example.api_coffeestore.model.ERole;
import com.example.api_coffeestore.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class UserDTO {
    Long id;
    String address;
    Date birthday;
    String email;
    String fullName;
    String img;
    String phone;
    String sex;
    String username;
    ERole role;
}

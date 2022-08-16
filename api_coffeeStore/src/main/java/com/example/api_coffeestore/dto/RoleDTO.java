package com.example.api_coffeestore.dto;

import com.example.api_coffeestore.model.ERole;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class RoleDTO {
    Integer id;
    ERole name;
}

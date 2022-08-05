package com.example.api_coffeestore.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class LoginRequest {
    @NotBlank
    String username;

    @NotBlank
    String password;
}

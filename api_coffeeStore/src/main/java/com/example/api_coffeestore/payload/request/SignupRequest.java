package com.example.api_coffeestore.payload.request;

import com.example.api_coffeestore.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private ERole role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    private String phone;
    @NotBlank
    private String fullName;
    private String img;
    private String address;
    @NotBlank
    private String sex;
    private Date birthday;

}

package com.example.api_coffeestore.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String fullname;
    private String address;
    private String birthday;
    private String sex;

    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, String phone,
                       String fullname, String address, String birthday, String sex, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;

        this.roles = roles;

    }


}

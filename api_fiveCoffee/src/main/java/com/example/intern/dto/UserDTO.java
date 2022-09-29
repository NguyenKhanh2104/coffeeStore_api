package com.example.intern.dto;
import com.example.intern.message.ResponseFile;
import com.example.intern.model.ERole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    Long id;
    String address;
    String birthday;
    String email;
    String fullName;
    ResponseFile imageUser;
    String phone;
    String sex;
    String username;
    ERole role;
}

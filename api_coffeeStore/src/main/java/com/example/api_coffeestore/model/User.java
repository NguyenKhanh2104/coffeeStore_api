package com.example.api_coffeestore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class    User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "img")
    private String img;

    @Column(name = "address")
    private String address;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "sex")
    private String sex;
    @OneToMany(mappedBy = "user")
    private List<Order> order;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public User( String username,String email, String password , String phone, String fullName, String img, String address, Date birthday, String sex) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.img = img;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;
    }
}

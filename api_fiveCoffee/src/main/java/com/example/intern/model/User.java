package com.example.intern.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
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

    @Column(name = "address")
    private String address;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "birthday")
    private String birthday;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageProduct")
    private FileDB imageUser;
    @Column(name = "sex")
    private String sex;
    @OneToMany(mappedBy = "user")
    private List<Order> order;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();



    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;


    }
    public User( String username,String email, String password , String phone, String fullName, String address, String birthday, String sex) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;
    }

}
package com.example.api_coffeestore.service.impl;

import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.model.User;
import com.example.api_coffeestore.repository.UserRepo;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    @Override
    public User findById(Long userId) throws Exception {
        return this.userRepository.findById(userId).orElseThrow(() ->new Exception("User is not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) throws Exception {
        User u = userRepository.findById(id).orElseThrow(() -> new Exception("User is not found"));
        u.setAddress(user.getAddress());
        u.setBirthday(user.getBirthday());
        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());
        u.setFullName(user.getFullName());
        u.setRoles(user.getRoles());
        u.setUsername(user.getUsername());
        u.setSex(user.getSex());
        return userRepository.save(u);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User create(User u) {
        return userRepository.save(u);
    }
}

package com.example.api_coffeestore.service.impl;

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
}

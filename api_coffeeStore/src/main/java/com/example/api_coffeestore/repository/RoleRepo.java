package com.example.api_coffeestore.repository;

import com.example.api_coffeestore.model.ERole;
import com.example.api_coffeestore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}

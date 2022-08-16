package com.example.api_coffeestore.repository;

import com.example.api_coffeestore.model.ERole;
import com.example.api_coffeestore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    @Query(value = "SELECT r FROM Role r WHERE r.name IN :name")
    Role findByName(@Param("name") ERole name);
}

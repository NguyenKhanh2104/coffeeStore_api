package com.example.intern.repository;

import com.example.intern.model.ERole;
import com.example.intern.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

//  @Query("Select role  FROM Role role WHERE role.name=:nameRole")
//  Optional<Role> findByName(@Param("nameRole") String nameRole);
  Optional<Role> findByName(ERole name);
}

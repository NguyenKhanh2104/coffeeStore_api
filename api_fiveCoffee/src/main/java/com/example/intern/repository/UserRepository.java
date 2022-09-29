package com.example.intern.repository;

import com.example.intern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);
  @Query(value = "SELECT b FROM User b WHERE b.fullName IN :fullName")
  User findByFullName(String fullName);
  Boolean existsByEmail(String email);
}

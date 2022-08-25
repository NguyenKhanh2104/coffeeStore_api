package com.example.api_coffeestore.repository;

import com.example.api_coffeestore.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}

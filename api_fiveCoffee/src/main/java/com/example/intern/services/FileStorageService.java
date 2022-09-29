package com.example.intern.services;

import com.example.intern.model.FileDB;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {


  public FileDB store(MultipartFile file) throws IOException ;



  public FileDB getFile(String id) ;


  
  public Stream<FileDB> getAllFiles() ;


}

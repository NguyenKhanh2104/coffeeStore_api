package com.example.api_coffeestore.service;
import com.example.api_coffeestore.model.FileDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


public interface FileStorageService {


  public FileDB store(MultipartFile file) throws IOException ;



  public FileDB getFile(String id) ;


  
  public Stream<FileDB> getAllFiles() ;


}

package com.example.api_coffeestore.service.impl;

import com.example.api_coffeestore.model.FileDB;
import com.example.api_coffeestore.repository.FileDBRepository;
import com.example.api_coffeestore.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Autowired
    private FileDBRepository fileDBRepository;

    @Override
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(),dtf.format(now));

        return fileDBRepository.save(FileDB);
    }

    @Override
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}

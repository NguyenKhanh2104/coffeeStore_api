package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.FileDTO;
import com.example.api_coffeestore.model.FileDB;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@Component
public class FileMapper {
    public FileDTO toDto(FileDB fileDB){
        FileDTO dto = new FileDTO();
        dto.setName(fileDB.getName());
        dto.setType(fileDB.getType());
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(fileDB.getId())
                .toUriString();
        dto.setUrl(fileDownloadUri);
        dto.setSize((long) fileDB.getData().length);
        return dto;
    }
}

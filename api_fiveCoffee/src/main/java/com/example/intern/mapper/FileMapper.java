package com.example.intern.mapper;

import com.example.intern.dto.FileDTO;
import com.example.intern.model.FileDB;
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

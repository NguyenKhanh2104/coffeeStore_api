package com.example.intern.helper;

import com.example.intern.dto.UserDTO;
import com.example.intern.mapper.UserMapper;
import com.example.intern.message.ResponseFile;
import com.example.intern.model.FileDB;
import com.example.intern.model.User;
import com.example.intern.services.FileStorageService;
import com.example.intern.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Component
public class UserHelper {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    FileStorageService fileStorageService;

    public ResponseEntity<?> getAll() {
        List<User> list = userService.findAll();
        List<UserDTO> rs = new ArrayList<>();
        for (User u : list
        ) {
            UserDTO dto = userMapper.toDto(u);
            rs.add(dto);
        }
        return ResponseEntity.ok(rs);
    }

    public User updateUser(Long id, UserDTO userDto) throws Exception {
        User user = userMapper.toEntity(userDto);
        Stream<FileDB> list = fileStorageService.getAllFiles();
        user.setImageUser(list.sorted(Comparator.comparing(FileDB::getDateCreate).reversed()).findFirst().get());
        return userService.updateUser(id, user);
    }

    public void removeUser(Long id) {
        userService.remove(id);
    }

    public User createUser(UserDTO userDto) {
        User u = userMapper.toEntity(userDto);
        return userService.create(u);
    }

    public List<ResponseFile> getImageUser(Long id) throws Exception {
        User user = userService.findById(id);
        List<ResponseFile> rs = new ArrayList<>();
        FileDB fileDB = fileStorageService.getFile(user.getImageUser().getId());
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(fileDB.getId())
                .toUriString();
        ResponseFile resp = new ResponseFile(
                fileDB.getName(),
                fileDownloadUri,
                fileDB.getType(),
                fileDB.getData().length);
        rs.add(resp);
        return rs;
    }
}

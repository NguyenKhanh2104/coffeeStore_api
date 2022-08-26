package com.example.api_coffeestore.controller;

import com.example.api_coffeestore.dto.UserDTO;
import com.example.api_coffeestore.mapper.UserMapper;
import com.example.api_coffeestore.message.ResponseMessage;
import com.example.api_coffeestore.model.ERole;
import com.example.api_coffeestore.model.FileDB;
import com.example.api_coffeestore.model.Role;
import com.example.api_coffeestore.model.User;
import com.example.api_coffeestore.payload.request.LoginRequest;
import com.example.api_coffeestore.payload.request.SignupRequest;
import com.example.api_coffeestore.payload.response.JwtResponse;
import com.example.api_coffeestore.repository.RoleRepo;
import com.example.api_coffeestore.repository.UserRepo;
import com.example.api_coffeestore.security.jwt.JwtUtils;
import com.example.api_coffeestore.service.FileStorageService;
import com.example.api_coffeestore.service.RoleService;
import com.example.api_coffeestore.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;
    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private FileStorageService storageService;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username or password incorrect");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                userDetails.getFullname(),
                userDetails.getAddress(),
                userDetails.getBirthday(),
                userDetails.getSex(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword()), request.getPhone(), request.getFullName(),
                 request.getAddress(), request.getBirthday(), request.getSex());

        List<Role> roles = roleService.findAll();
        List<Role> rs = new ArrayList<>();
        for (Role r:roles
             ) {
            if (r.getName().equals(request.getRole()))
            {
                rs.add(r);
            }
        }
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN);
//                        roles.add(adminRole);
//
//                        break;
//                    default:
//                        Role userRole = roleService.findByName(ERole.ROLE_USER);
//                        roles.add(userRole);
//                }
//            });


        user.setRoles(rs);
        Stream<FileDB> list = storageService.getAllFiles();
        user.setImageUser(list.sorted(Comparator.comparing(FileDB::getDateCreate).reversed()).findFirst().get());
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
//	@PostMapping("/updatePost")
//	public ResponseEntity<?> saveQuiz(@RequestBody @Valid PostRequest postRequest) {
//		return postHelper.save(postRequest);
//	}
@PostMapping("/upload")
public ResponseMessage uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
        storageService.store(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        ResponseMessage r = new ResponseMessage();
        return r;
    } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return  new ResponseMessage();
    }
}
}

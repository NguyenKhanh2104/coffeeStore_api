package com.example.intern.controllers;
import com.example.intern.dto.*;
import com.example.intern.helper.*;
import com.example.intern.mapper.FileMapper;
import com.example.intern.message.ResponseFile;
import com.example.intern.message.ResponseMessage;
import com.example.intern.model.*;
import com.example.intern.payload.request.SignupRequest;
import com.example.intern.payload.response.MessageResponse;
import com.example.intern.repository.RoleRepository;
import com.example.intern.repository.UserRepository;
import com.example.intern.services.FileStorageService;
import com.example.intern.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    ProductHelper productHelper;
    @Autowired
    CartHelper cartHelper;
    @Autowired
    UserHelper userHelper;
    @Autowired
    OrderHelper orderHelper;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    OrderItemHelper orderItemHelper;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private FileMapper fileMapper;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), signUpRequest.getFullName(),
                signUpRequest.getAddress(), signUpRequest.getBirthday(), signUpRequest.getSex());


        Set<Role> roles = new HashSet<>();
                Set<String> strRoles = new HashSet<>();
                strRoles.add(signUpRequest.getRole());
        if (signUpRequest.getRole() == null) {
            Role userRole = roleRepository.findByName(com.example.intern.model.ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(com.example.intern.model.ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        Stream<FileDB> list = storageService.getAllFiles();
        user.setImageUser(list.sorted(Comparator.comparing(FileDB::getDateCreate).reversed()).findFirst().get());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @GetMapping("/allProduct")
    public ResponseEntity<?> getAllProduct() {
        return productHelper.getAll();
    }

    @GetMapping("/find/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id) throws Exception {
        return productHelper.findById(id);
    }

    @GetMapping("/findAllCart")
    public List<CartDTO> findAll() {
        return cartHelper.findAll();
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long id,
                                 @Valid @RequestBody ProductDTO productDetail) throws Exception {
        return productHelper.updateProduct(id, productDetail);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {

            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping(value = "/addProduct", consumes = {"application/json"})
    public Product createBook(@Valid @RequestBody ProductDTO productDto) throws Exception {
        return productHelper.createProduct(productDto);
    }

    @DeleteMapping("/removeProduct/{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        productHelper.removeProduct(id);
    }

    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUser() {
        return userHelper.getAll();
    }


    @DeleteMapping("/removeUser/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userHelper.removeUser(id);
    }

    @PostMapping(value = "/addUser", consumes = {"application/json"})

    public User createUser(@Valid @RequestBody UserDTO userDto) throws Exception {
        return userHelper.createUser(userDto);
    }


    @GetMapping("/allOrder")

    public List<OrderDTO> getAllOrder() {
        return orderHelper.findAll();
    }

    @PutMapping("/updateUser/{id}")
    public User updateProduct(@PathVariable(value = "id") Long id,
                              @Valid @RequestBody UserDTO userDto) throws Exception {
        return userHelper.updateUser(id, userDto);
    }

    @PutMapping("/updateOrder/{id}")
    public Order updateOrder(@PathVariable(value = "id") String id,
                             @Valid @RequestBody OrderDTO orderDetail) throws Exception {
        return orderHelper.updateOrder(id, orderDetail);
    }

    @DeleteMapping("/removeOrder/{id}")
    public void deleteOrder(@PathVariable(value = "id") String id) {
        orderHelper.removeOrder(id);
    }

    @GetMapping("/getOrderItem/{id}")
    public List<OrderItemDTO> getOrderItemByOrderId(@PathVariable("id") String id) {
        return orderItemHelper.getOrderItemByOrderId(id);
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> rs = new ArrayList<>();
        return ResponseEntity.ok(rs);


    }
    @GetMapping("/totalMoneyInMonth")
    public List<Double> getTotalMoney () {
        return orderHelper.getTotalMoney();
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/getImageUser/{id}")
    public List<ResponseFile> getImageUser(@PathVariable Long id) throws Exception {

        return userHelper.getImageUser(id);
    }

}

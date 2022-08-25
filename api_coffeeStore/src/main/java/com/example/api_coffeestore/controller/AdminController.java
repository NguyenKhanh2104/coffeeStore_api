package com.example.api_coffeestore.controller;

import com.example.api_coffeestore.dto.*;
import com.example.api_coffeestore.helper.*;
import com.example.api_coffeestore.mapper.FileMapper;
import com.example.api_coffeestore.message.ResponseFile;
import com.example.api_coffeestore.message.ResponseMessage;
import com.example.api_coffeestore.model.*;
import com.example.api_coffeestore.service.CartService;
import com.example.api_coffeestore.service.FileStorageService;
import com.example.api_coffeestore.service.OrderItemService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    OrderItemHelper orderItemHelper;
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private FileMapper fileMapper;

    @GetMapping("/allProduct")
    public ResponseEntity<?> getAllBook() {
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

    @PostMapping(value = "/addProduct", consumes = {"application/json"})
    public Product createBook(@Valid @RequestBody ProductDTO productDto) throws Exception {
        return productHelper.createProduct(productDto);
    }

    @DeleteMapping("/removeProduct/{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        productHelper.removeProduct(id);
    }

    @GetMapping("/allUser")
    public List<UserDTO> getAllUser() {
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

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
//        List<FileDB> files = storageService.getAllFiles();
        List<ResponseFile> rs = new ArrayList<>();
//        for (FileDB f : files
//        ) {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(f.getId())
//                    .toUriString();
//            ResponseFile resp = new ResponseFile(f.getName(),fileDownloadUri,f.getType(),f.getData().length);
//            rs.add(resp);
//        }
        return ResponseEntity.ok(rs);


    }

//                .map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length);
//        }).collect(Collectors.toList());


    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

}

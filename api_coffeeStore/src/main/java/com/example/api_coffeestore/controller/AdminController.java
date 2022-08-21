package com.example.api_coffeestore.controller;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.dto.UserDTO;
import com.example.api_coffeestore.helper.CartHelper;
import com.example.api_coffeestore.helper.Categoryhelper;
import com.example.api_coffeestore.helper.ProductHelper;
import com.example.api_coffeestore.helper.UserHelper;
import com.example.api_coffeestore.model.Category;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.model.User;
import com.example.api_coffeestore.service.CartService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/allProduct")
    public List<ProductDTO> getAllBook() {
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

    @PutMapping("/updateUser/{id}")
    public User updateProduct(@PathVariable(value = "id") Long id,
                              @Valid @RequestBody UserDTO userDto) throws Exception {
        return userHelper.updateUser(id, userDto);
    }

    @DeleteMapping("/removeUser/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userHelper.removeUser(id);
    }

    @PostMapping(value = "/addUser", consumes = {"application/json"})
    public User createUser(@Valid @RequestBody UserDTO userDto) throws Exception {
        return userHelper.createUser(userDto);
    }
}
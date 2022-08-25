package com.example.api_coffeestore.controller;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.OrderDTO;
import com.example.api_coffeestore.dto.OrderItemDTO;
import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.helper.*;
import com.example.api_coffeestore.message.ResponseMessage;
import com.example.api_coffeestore.model.*;
import com.example.api_coffeestore.payload.response.ApiResponse;
import com.example.api_coffeestore.security.ShoppingConfiguration;
import com.example.api_coffeestore.service.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    ProductHelper productHelper;
    @Autowired
    Categoryhelper categoryhelper;
    @Autowired
    CartHelper cartHelper;
    @Autowired
    OrderService orderService;


    @Autowired
    OrderHelper orderHelper;
    @Autowired
    OrderItemHelper orderItemHelper;

    @GetMapping("/allProduct")
    public ResponseEntity<?> getAllBook() {
        return productHelper.getAll();
    }

    @GetMapping("/find/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id) throws Exception {
        return productHelper.findById(id);
    }

    @RequestMapping("addCart")
    public ResponseEntity<?> addCartWithProduct(@RequestBody HashMap<String, String> addCartRequest) {
        return cartHelper.addCartWithProduct(addCartRequest);

    }

    @RequestMapping("updateQtyForCart")
    public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String, String> addCartRequest) {
        return cartHelper.updateQtyForCart(addCartRequest);
    }

    @RequestMapping("removeProductFromCart")
    public ResponseEntity<?> removeCartWithProductId(@RequestBody HashMap<String, String> removeCartRequest) {
        return cartHelper.removeCartwithProductId(removeCartRequest);
    }

    @RequestMapping("getCartsByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String, String> getCartRequest) {
        return cartHelper.getCartsByUserId(getCartRequest);
    }

    @GetMapping("/findByCategory/{id}")
    public List<ProductDTO> getListProductById(@PathVariable("id") Integer id) {
        return productHelper.findByCategory(id);
    }

    @GetMapping("/allCategory")
    public List<Category> getAllCategory() {
        return categoryhelper.findAll();
    }

    @GetMapping("/findAllCart")
    public List<CartDTO> findAll() {
        return cartHelper.findAll();
    }

    @RequestMapping("checkout")
    public ResponseEntity<?> checkout_order(@RequestBody HashMap<String, String> addCartRequest) {
       return orderHelper.checkout_order(addCartRequest);
    }
    @GetMapping("/lastOrder")
    public List<OrderDTO> getLastOrder() {

        return orderHelper.getLast();
    }

    @GetMapping("/lastOrderItem")
    public List<OrderItemDTO> getLastOrderItem() {
        return orderItemHelper.getByOrderId();
    }
    @RequestMapping("getCheckoutsByUserId")
    public ResponseEntity<?> getCheckoutsByUserId(@RequestBody HashMap<String, String> getCheckoutRequest) {
        return orderHelper.getCheckoutsByUserId(getCheckoutRequest);
    }

}

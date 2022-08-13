package com.example.api_coffeestore.controller;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.helper.CartHelper;
import com.example.api_coffeestore.helper.Categoryhelper;
import com.example.api_coffeestore.helper.ProductHelper;
import com.example.api_coffeestore.model.Cart;
import com.example.api_coffeestore.model.Category;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.payload.response.ApiResponse;
import com.example.api_coffeestore.security.ShoppingConfiguration;
import com.example.api_coffeestore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

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
    CartService cartService;

    @GetMapping("/allProduct")
    public List<ProductDTO> getAllBook() {
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
}

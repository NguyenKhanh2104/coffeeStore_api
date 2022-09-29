package com.example.intern.controllers;
import com.example.intern.dto.CartDTO;
import com.example.intern.dto.OrderDTO;
import com.example.intern.dto.OrderItemDTO;
import com.example.intern.dto.ProductDTO;
import com.example.intern.helper.*;
import com.example.intern.message.ResponseFile;
import com.example.intern.message.ResponseMessage;
import com.example.intern.model.Category;
import com.example.intern.services.FileStorageService;
import com.example.intern.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private FileStorageService storageService;
    @GetMapping("/allProduct")
    public ResponseEntity<?> getAllProduct() {
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCheckoutsByUserId(@RequestBody HashMap<String, String> getCheckoutRequest) {
        return orderHelper.getCheckoutsByUserId(getCheckoutRequest);
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
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}

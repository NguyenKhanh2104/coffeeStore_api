package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.mapper.CartMapper;
import com.example.api_coffeestore.model.Cart;
import com.example.api_coffeestore.payload.response.ApiResponse;
import com.example.api_coffeestore.security.ShoppingConfiguration;
import com.example.api_coffeestore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartHelper {
    @Autowired
    CartService cartService;
    @Autowired
    CartMapper cartMapper;
    public ResponseEntity<?> addCartWithProduct(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"productId","userId","qty","price"};
            if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            Long productId = Long.parseLong(addCartRequest.get("productId"));
            Long userId =  Long.parseLong(addCartRequest.get("userId"));
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double price = Double.parseDouble(addCartRequest.get("price"));

            List<Cart> obj = cartService.addCartByUserIdAndProductId(productId,userId,qty,price);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }
    public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"cartId","userId","qty","price"};
            if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            Long cartId = Long.parseLong(addCartRequest.get("cartId"));
            Long userId =  Long.parseLong(addCartRequest.get("userId"));
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double price = Double.parseDouble(addCartRequest.get("price"));
            cartService.updateQtyByCartId(cartId, qty, price);
            List<Cart> obj = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }
    public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest) {
        try {
            String keys[] = {"userId","cartId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

            }
            List<Cart> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")), Long.parseLong(removeCartRequest.get("userId")));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
            }
            List<Cart> obj = cartService.getCartByUserId(Long.parseLong(getCartRequest.get("userId")));
            List<CartDTO> rs = new ArrayList<>();
            for (Cart c:obj
                 ) {
                CartDTO dto = cartMapper.toDto(c);
                rs.add(dto);
            }
            return ResponseEntity.ok(rs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    public List<CartDTO> findAll(){
        List<CartDTO> rs = new ArrayList<>();
        List<Cart> list = cartService.findAll();
        for (Cart c: list
             ) {
            CartDTO dto = cartMapper.toDto(c);
            rs.add(dto);
        }
        return rs;
    }
}

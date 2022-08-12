package com.example.api_coffeestore.mapper;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.model.Cart;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.service.ProductService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    public CartDTO toDto(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setNameProduct(cart.getProduct().getName());
        dto.setPrice(cart.getPrice());
        dto.setPriceProduct(cart.getProduct().getPrice());
        dto.setQty(cart.getQty());
        dto.setUser_id(cart.getUser().getId());
        return dto;
    }
    public Cart toEntity(CartDTO cartDTO) throws Exception {
        Cart c = new Cart();
        c.setId(cartDTO.getId());
        c.setPrice(cartDTO.getPrice());
        c.setUser(userService.findById(cartDTO.getUser_id()));
        c.setProduct(productService.findByName(cartDTO.getNameProduct()));
        c.setQty(cartDTO.getQty());
        return c;
    }
}

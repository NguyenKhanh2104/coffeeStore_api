package com.example.intern.mapper;
import com.example.intern.dto.CartDTO;
import com.example.intern.model.Cart;
import com.example.intern.services.ProductService;
import com.example.intern.services.UserService;
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
        return dto;
    }
    public Cart toEntity(CartDTO cartDTO) throws Exception {
        Cart c = new Cart();
        c.setPrice(cartDTO.getPrice());
        c.setProduct(productService.findByName(cartDTO.getNameProduct()));
        c.setQty(cartDTO.getQty());
        return c;
    }
}

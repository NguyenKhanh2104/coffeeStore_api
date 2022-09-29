package com.example.intern.services;
import com.example.intern.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CartService {
    List<Cart> findAll();
    List<Cart> addCartByUserIdAndProductId(Long productId, Long user_id, int qty, double price) throws Exception;
    void updateQtyByCartId(long cartId,int qty,double price) throws Exception;
    void updateQtyByProductId(Long product_id,int qty,double price) throws  Exception;
        List<Cart> getCartByUserId(Long user_id);
    Cart getCartByProductId(Long product_id);
    List<Cart> removeCartByUserId(long cartId,Long user_id);
    List<Cart> removeAllCartByUserId(Long user_id);
}

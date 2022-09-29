package com.example.intern.services.Impl;


import com.example.intern.model.Cart;
import com.example.intern.model.Product;
import com.example.intern.repository.CartRepo;
import com.example.intern.services.CartService;
import com.example.intern.services.ProductService;
import com.example.intern.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CartRepo cartRepo;
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public List<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    public List<Cart> addCartByUserIdAndProductId(Long productId, Long user_id, int qty, double price) throws Exception {
        Cart obj = new Cart();
        int count = 1;
        try {
            if (cartRepo.getCartByProductIdAndUserId(user_id, productId).isPresent()) {
                Cart a = cartRepo.getCartByProductId(productId);
                cartRepo.updateQtyByProductId(productId, a.getProduct().getPrice() * (a.getQty() + count), a.getQty() + count);
            } else {

                obj.setQty(qty);
                obj.setUser(userService.findById(user_id));
                Product product = productService.findById(productId);
                obj.setProduct(product);

                obj.setPrice(price);
                cartRepo.save(obj);

            }
            count++;
            System.err.println("count is after add " + count);
            return this.getCartByUserId(user_id);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
        cartRepo.updateQtyByCartId(cartId, price, qty);
    }

    @Override
    public void updateQtyByProductId(Long product_id, int qty, double price) throws Exception {
        cartRepo.updateQtyByProductId(product_id, price, qty);
    }

    @Override
    public List<Cart> getCartByUserId(Long user_id) {
        return cartRepo.getCartByUserId(user_id);
    }

    @Override
    public Cart getCartByProductId(Long product_id) {
        return cartRepo.getCartByProductId(product_id);
    }

    @Override
    public List<Cart> removeCartByUserId(long cartId, Long user_id) {
        cartRepo.deleteCartByIdAndUserId(user_id, cartId);
        return this.getCartByUserId(user_id);
    }

    @Override
    public List<Cart> removeAllCartByUserId(Long user_id) {
        cartRepo.deleteAllCartByUserId(user_id);
        return null;
    }
}

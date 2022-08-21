package com.example.api_coffeestore.controller;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.OrderDTO;
import com.example.api_coffeestore.dto.OrderItemDTO;
import com.example.api_coffeestore.dto.ProductDTO;
import com.example.api_coffeestore.helper.*;
import com.example.api_coffeestore.model.*;
import com.example.api_coffeestore.payload.response.ApiResponse;
import com.example.api_coffeestore.security.ShoppingConfiguration;
import com.example.api_coffeestore.service.CartService;
import com.example.api_coffeestore.service.OrderItemService;
import com.example.api_coffeestore.service.OrderService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderHelper orderHelper;
    @Autowired
    OrderItemHelper orderItemHelper;

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

    @RequestMapping("checkout")
    public ResponseEntity<?> checkout_order(@RequestBody HashMap<String, String> addCartRequest) {
        try {
            String keys[] = {"userId", "total_price", "pay_type", "note"};
            if (ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            Long user_Id = Long.parseLong(addCartRequest.get("userId"));
            double total_amt = Double.parseDouble(addCartRequest.get("total_price"));
            if (orderService.checkTotalAmountAgainstCart(total_amt, user_Id)) {
                double total = 0;
                List<Cart> cartItems = cartService.getCartByUserId(user_Id);
                for (Cart a : cartItems
                ) {
                    System.err.println("tron gio hang co " + a);
                }
                List<OrderItem> tmp = new ArrayList<>();
                List<Order> listCheckout = new ArrayList<>();
                Order checkout = new Order();
                checkout.setUser(userService.findById(user_Id));
                checkout.setNote(addCartRequest.get("note"));
                checkout.setPayment_type(addCartRequest.get("pay_type"));
                Date date = new Date();
                checkout.setDateCreate(date);
                String da = String.valueOf(date.getTime());
                checkout.setId("HD" + da);
                checkout = orderService.saveProductsForCheckout(checkout);
                for (Cart addCart : cartItems) {
                    OrderItem checkout_item = new OrderItem();

                    checkout_item.setPrice(addCart.getPrice());
                    checkout_item.setProduct(addCart.getProduct());
                    checkout_item.setQty(addCart.getQty());
                    checkout_item.setOrder(checkout);
                    total += addCart.getPrice();
                    tmp.add(checkout_item);
                }
                checkout.setTotalPrice(total);
                checkout.setOrderItem(tmp);
//                checkoutItemService.saveCheckoutItem(tmp);
                for (OrderItem c : tmp
                ) {

                    orderItemService.save(c);
                    System.err.println("phan tu da them " + c);
                }

                orderService.saveProductsForCheckout(checkout);
                cartService.removeAllCartByUserId(user_Id);
                orderService.getAllCheckoutByUserId(user_Id);
                System.out.println("da xoa cart");
                return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
            } else {
                throw new Exception("Total amount is mismatch");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    //    @GetMapping("/getOrder")
//    public OrderDTO getLastOrder() {
//        return orderHelper.getLatsOrder();
//    }
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

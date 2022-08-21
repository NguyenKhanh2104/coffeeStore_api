package com.example.api_coffeestore.helper;

import com.example.api_coffeestore.dto.CartDTO;
import com.example.api_coffeestore.dto.OrderDTO;
import com.example.api_coffeestore.dto.OrderItemDTO;
import com.example.api_coffeestore.mapper.OrderMapper;
import com.example.api_coffeestore.model.Cart;
import com.example.api_coffeestore.model.Order;
import com.example.api_coffeestore.model.OrderItem;
import com.example.api_coffeestore.model.Product;
import com.example.api_coffeestore.payload.response.ApiResponse;
import com.example.api_coffeestore.security.ShoppingConfiguration;
import com.example.api_coffeestore.service.CartService;
import com.example.api_coffeestore.service.OrderItemService;
import com.example.api_coffeestore.service.OrderService;
import com.example.api_coffeestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderHelper {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;
    public List<OrderDTO> findAll(){
        List<OrderDTO> rs = new ArrayList<>();
        List<Order> list = orderService.findAll();
        for (Order o:list
             ) {
            OrderDTO dto = orderMapper.toDto(o);
            rs.add(dto);
        }
        return rs;
    }
    public List<OrderDTO> getLast(){
        List<Order> listOrder = orderService.findAll();
        List<OrderDTO> dto = new ArrayList<>();
            Order order = listOrder.get(listOrder.size()-1);
            OrderDTO orderDto = orderMapper.toDto(order);
            dto.add(orderDto);

        return dto;
    }

    public OrderDTO findById(String id) throws Exception {
        Order o = orderService.getById(id);
        OrderDTO dto  = orderMapper.toDto(o);
        return dto;
    }

    public ResponseEntity<?> getCheckoutsByUserId(HashMap<String, String> getCheckoutRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCheckoutRequest)) {
            }
            List<Order> obj = orderService.getCheckoutsByUserId(Long.parseLong(getCheckoutRequest.get("userId")));
            List<OrderDTO> rs = new ArrayList<>();
            for (Order o:obj
            ) {
                OrderDTO dto = orderMapper.toDto(o);
                rs.add(dto);
            }
            return ResponseEntity.ok(rs);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
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

    public Order updateOrder(String id, OrderDTO orderDetail) throws Exception {
        Order o = orderMapper.toEntity(orderDetail);
        return orderService.updateOrder(id,o);
    }

    public void removeOrder(String id) {
        orderService.remove(id);
    }


}

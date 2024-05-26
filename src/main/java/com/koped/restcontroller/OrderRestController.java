package com.koped.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import com.koped.enums.OrderStatus;
import com.koped.model.Cart;
import com.koped.model.Product;
import com.koped.service.CartService;
import com.koped.service.ProductService;
import org.springframework.web.bind.annotation.*;

import com.koped.model.Order;
import com.koped.service.OrderServiceImpl;
import com.koped.service.CartServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderServiceImpl orderService;
    private final CartService cartService;
    private final ProductService productService;


    @GetMapping("/list")
    public List<Order> findAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/search/{orderId}")
    public Order findByOrderId(@PathVariable String orderId) {
        return orderService.getOrderById(Integer.parseInt(orderId));
    }

//    @PostMapping("/create")
//    public Order createOrder(@RequestParam int userId, @RequestBody List<Cart> selectedItems) {
//        List<OrderProduct> orderProducts = selectedItems.stream().map(cart -> {
//            Product product = productService.findByProductIds(cart.getProductId());
//            OrderProduct orderProduct = new OrderProduct();
//            orderProduct.setProductId(cart.getProductId());
//            orderProduct.setTitle(product.getTitle());
//            orderProduct.setQuantity(cart.getQuantity());
//            orderProduct.setPrice(cart.getPrice());
//            orderProduct.setImage(product.getImage());
//            return orderProduct;
//        }).collect(Collectors.toList());
//
//        Order order = new Order();
//        order.setUserId(userId);
//        order.setStatus(OrderStatus.PLACED);
//        orderService.saveOrder(order);
//
//        // Log the order details for debugging
//        System.out.println("Created Order: " + order);
//
//        // Clear the selected items from the cart
//        selectedItems.forEach(cart -> cartService.removeProductFromCart(cart.getId(), cart.getProductId()));
//
//        return order;
//    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderByOrderId(@PathVariable String orderId) {
        return orderService.deleteOrder(Integer.parseInt(orderId));
    }

    @PutMapping("/update")
    public Order updateOrderStatus(@PathVariable String orderId, @PathVariable String status) {
        return orderService.updateStatus(Integer.parseInt(orderId), status);
    }

}
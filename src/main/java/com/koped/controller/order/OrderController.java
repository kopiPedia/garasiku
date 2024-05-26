package com.koped.controller.order;

import com.koped.model.Cart;
import com.koped.model.Order;
import com.koped.service.CartService;
import com.koped.service.OrderService;
import com.koped.service.ProductService;
import com.koped.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String listOrders(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = orderService.getOrdersByUserId(username);
        model.addAttribute("orders", orders);
        return "order_page";
    }

    @GetMapping("/order")
    public String orderPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order_page";
    }

    @PostMapping("/create/{cartId}")
    public String createOrdersFromCart(@PathVariable Long cartId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Cart> carts = cartService.findCartByUser(username);

        for (Cart cart : carts) {
            if (cart.getId() == cartId) {
                Order order = new Order();
                order.setProductId(cart.getProductId());
                order.setOrderDate(Timestamp.from(Instant.now()));
                order.setUserId(username);
                orderService.saveOrder(order);
                cartService.removeProductFromCart(cartId,cart.getProductId());
            }
        }
        return "redirect:/orders/list";
    }
}

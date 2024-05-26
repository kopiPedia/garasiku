package com.koped.controller.order;
import com.koped.repository.ProductRepository;
import com.koped.service.OrderService;
import com.koped.service.ProductService;
import com.koped.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.koped.model.Cart;
import com.koped.model.Order;
import com.koped.service.CartService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
        List<Order> orders = orderService.getAllOrders();
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
        // Fetch the cart using cartId
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Cart> carts = cartService.findCartByUser(username);
        Order order1 = new Order();
        // Iterate over each cart
        for (Cart cart : carts) {
                // Split productId string into a list
            String prodId = cart.getProductId();
            order1.setProductId(prodId);
            order1.setOrderDate(Timestamp.from(Instant.now()));
            order1.setUserId(username);
            orderService.saveOrder(order1);
            }

        return "redirect:/order";
    }


}


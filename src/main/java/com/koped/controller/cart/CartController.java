package com.koped.controller.cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.koped.model.Cart;
import com.koped.service.CartService;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/cart")
    public String cartPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List <Cart> cart = cartService.findCartByUser(username);
        model.addAttribute("cart", cart);
        model.addAttribute("total", cartService.getPriceCart(username));
        return "cart";
    }
}
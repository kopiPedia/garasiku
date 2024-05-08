package com.koped.controller.cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.koped.model.Cart;
import com.koped.service.CartService;
import java.util.List;
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/cart")
    public String cartPage(Model model) {
        List <Cart> cart = cartService.findCartByUser("john_doe");
        model.addAttribute("cart", cart);
        return "cart";
    }
}
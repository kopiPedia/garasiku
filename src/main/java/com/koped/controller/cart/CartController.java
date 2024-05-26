package com.koped.controller.cart;
import com.koped.service.ImportProductService;
import com.koped.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.koped.model.Cart;
import com.koped.service.CartService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImportProductService importService;
    @GetMapping("/cart")
    public String cartPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List <Cart> cart = cartService.findCartByUser(username);
        List <String> images = new ArrayList<String>();
        double total = 0;
        for(Cart i : cart){
            images.add(productService.findByProductIds(i.getProductId()).getImage());
            total += productService.findByProductIds(i.getProductId()).getPrice() * i.getQuantity();
        }
        model.addAttribute("username", username);
        model.addAttribute("images", images);
        model.addAttribute("cart", cart);
        model.addAttribute("total",total );
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addProductToCart(@RequestParam int quantity, @RequestParam String productId, @RequestParam String username, Model model){
        Cart cart = new Cart();
        cart.setQuantity(quantity);
        cart.setProductId(productId);
        cart.setUsername(username);
        cart.setPrice(productService.findByProductIds(productId).getPrice());
        cart.setProduct(productService.findByProductIds(productId).getTitle());
        cartService.addProductToCart(cart);
        model.addAttribute("product", productService.findByProductIds(productId));
        model.addAttribute("cart", cart);
        model.addAttribute("username", username);
        return "product/detail-product";
    }

    @PostMapping("/cart/addImportForm")
    public String addProductToCartImportForm(@RequestParam int quantity, @RequestParam String productId, @RequestParam String username, Model model){
            Cart cart = new Cart();
            cart.setQuantity(quantity);
            cart.setProductId(productId);
            cart.setUsername(username);
            cart.setPrice(importService.findByProductId(productId).getPrice());
            cart.setProduct(importService.findByProductId(productId).getTitle());
            cartService.addProductToCartImportProduct(cart);
            model.addAttribute("cart", cart);
            model.addAttribute("username", username);
            model.addAttribute("importProduct", importService.findByProductId(productId));
            return "Import/view-import-product";
    }
    @GetMapping("/cart/delete/{id}/{productId}")
    public String removeProductFromCart(@PathVariable("id") long id, @PathVariable("productId") String productId) {
        cartService.removeProductFromCart(id, productId);
        return "redirect:/cart";
    }

}
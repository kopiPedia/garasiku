package com.koped.restcontroller;
import java.util.List;

import com.koped.service.CartServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.koped.model.Cart;


import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartRestController {
    private final CartServiceImpl cartService;

    @GetMapping("/list/{user}")
    public List<Cart> findCartByUser(@PathVariable String user){
        return cartService.findCartByUser(user);
    }
    @PostMapping("/add")
    public Cart addProductToCart(@RequestBody Cart cart){
        return cartService.addProductToCart(cart);
    }
    @DeleteMapping("/delete/{id}")
    public void removeProductFromCart(@PathVariable long id){
        cartService.removeProductFromCart(id);
    }
    @PutMapping("/update/{id}/{quantity}")
    public Cart updateProductQuantityInCart(@PathVariable long id, @PathVariable int quantity){
        return cartService.updateProductQuantityInCart(id, quantity);
    }
    @GetMapping("/total/{user}")
    public int gettotalCart(@PathVariable String user){
        return cartService.gettotalCart(user);
    }
    @GetMapping("/price/{user}")
    public int getPriceCart(@PathVariable String user){
        return cartService.getPriceCart(user);
    }
}

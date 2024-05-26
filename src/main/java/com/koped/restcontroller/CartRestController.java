package com.koped.restcontroller;
import java.math.BigDecimal;
import java.util.List;

import com.koped.service.CartServiceImpl;
import com.koped.service.ImportProductServiceImpl;
import com.koped.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/update/{id}/{quantity}/{productId}")
    public Cart updateProductQuantityInCart(@PathVariable long id, @PathVariable int quantity, @PathVariable String productId){
        return cartService.updateProductQuantityInCart(id, quantity, productId);
    }
    @GetMapping("/total/{user}")
    public int gettotalCart(@PathVariable String user){
        return cartService.gettotalCart(user);
    }
    @GetMapping("/price/{id}")
    public double getPriceCart(@PathVariable long id){
        return cartService.getPriceCart(id);
    }
    @PutMapping("/decrease/{id}/{productId}")
    public Cart decreaseProductQuantityInCart(@PathVariable long id, @PathVariable String productId){
        return cartService.decreaseProductQuantityInCart(id, productId);
    }
    @PutMapping("/increase/{id}/{productId}")
    public Cart increaseProductQuantityInCart(@PathVariable long id, @PathVariable String productId){
        return cartService.increaseProductQuantityInCart(id, productId);
    }
}
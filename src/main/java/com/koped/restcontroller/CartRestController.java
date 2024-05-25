package com.koped.restcontroller;
import java.math.BigDecimal;
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
    @DeleteMapping("/delete/{id}/{productId}")
    public void removeProductFromCart(@PathVariable long id, @PathVariable String productId){
        cartService.removeProductFromCart(id, productId);
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
    public BigDecimal getPriceCart(@PathVariable long id){
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

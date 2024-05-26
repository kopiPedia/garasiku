package com.koped.restcontroller;
import java.math.BigDecimal;
import java.util.List;

import com.koped.service.CartServiceImpl;
import com.koped.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.koped.model.Cart;


import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartRestController {
    private final CartServiceImpl cartService;
    private final ProductServiceImpl productService;
    @GetMapping("/list/{user}")
    public List<Cart> findCartByUser(@PathVariable String user){
        return cartService.findCartByUser(user);
    }
    @PostMapping("/add")
    public Cart addProductToCart(@RequestParam int quantity, @RequestParam String productId, @RequestParam String username){
        Cart cart = new Cart();
        cart.setQuantity(quantity);
        cart.setProductId(productId);
        cart.setUsername(username);
        cart.setPrice(productService.findByProductIds(productId).getPrice());
        cart.setProduct(productService.findByProductIds(productId).getTitle());
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

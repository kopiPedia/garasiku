package com.koped.service;
import com.koped.model.Cart;

import java.math.BigDecimal;
import java.util.List;
public interface CartService {
    Cart addProductToCart(Cart cart);
    void removeProductFromCart(long id, String productId);
    Cart updateProductQuantityInCart(long id, int quantity, String productId);
    int gettotalCart(String username);
    double getPriceCart(long id);
    List<Cart> findCartByUser(String username);
    Cart decreaseProductQuantityInCart(long id, String productId);
    Cart increaseProductQuantityInCart(long id, String productId);
}

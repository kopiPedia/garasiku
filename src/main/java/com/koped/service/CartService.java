package com.koped.service;
import com.koped.model.Cart;
public interface CartService {
    Cart addProductToCart(Cart cart);
    void removeProductFromCart(long id);
    Cart updateProductQuantityInCart(long id, int quantity);
    int gettotalCart(String user);
    int getPriceCart(String user);
}

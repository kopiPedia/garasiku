package com.koped.service;
import com.koped.model.Cart;
import java.util.List;
public interface CartService {
    Cart addProductToCart(Cart cart);
    void removeProductFromCart(long id);
    Cart updateProductQuantityInCart(long id, int quantity);
    int gettotalCart(String username);
    int getPriceCart(String username);
    List<Cart> findCartByUser(String username);
}

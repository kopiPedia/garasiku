package com.koped.service;

import com.koped.model.Cart;
import com.koped.model.Product;
import com.koped.model.User;
import com.koped.repository.CartRepository;
import com.koped.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Service
//@RequiredArgsConstructor
public class CartServiceImpl {

//
//    private CartRepository cartRepository;
//
//    @Override
//    public Cart addProductToCart(Cart cart) {
//        cartRepository.create(cart);
//        return cart;
//    }
//
//    @Override
//    public void removeProductFromCart(int id) {
//        cartRepository.deleteByUserAndProduct(id);
//    }
//
//    @Override
//    public Cart updateProductQuantityInCart(int id, int quantity) {
//        Cart cart = cartRepository.findByUserAndProduct(id,quantity);
//        if (cart != null) {
//            cart.setQuantity(quantity);
//            cartRepository.update(cart.getId(), cart);
//        }
//        return cart;
//    }
//
//    @Override
//    public int gettotalCart(User user) {
//        int totalItem = 0;
//        Iterator<Cart> cartIterator = cartRepository.findAllByUser(user);
//        List<Cart> userCart = new ArrayList<>();
//        cartIterator.forEachRemaining(userCart::add);
//        for(Cart i : userCart){
//            totalItem = totalItem + i.getQuantity();
//        }
//        return totalItem;
//    }
//
//    @Override
//    public int getPriceCart(User user) {
//        int totalPrice = 0;
//        Iterator<Cart> cartIterator = cartRepository.findAllByUser(user);
//        List<Cart> userCart = new ArrayList<>();
//        cartIterator.forEachRemaining(userCart::add);
//        for(Cart i : userCart){
//             totalPrice = totalPrice + (i.getQuantity() * i.getPrice());
//        }
//        return totalPrice;
//    }
}

package com.koped.service;
import com.koped.model.Cart;
import com.koped.repository.CartRepository;
import com.koped.repository.ImportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.koped.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService{
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final ImportRepository importRepository;

    @Override
    public Cart addProductToCart(Cart cart) {
        if(productRepository.findByProductId(cart.getProductId()).getStock() < cart.getQuantity()){
            // return null if stock is not enough
            return null;
        }
        if(cartRepository.findByProductIdAndUsername(cart.getProductId(), cart.getUsername()) != null){
            // if product already in cart, increase quantity
            Cart cart1 = cartRepository.findByProductIdAndUsername(cart.getProductId(), cart.getUsername());
            cart1.setQuantity(cart1.getQuantity() + cart.getQuantity());
            cart1.setPrice( cart1.getPrice()+cart.getPrice());
            productRepository.findByProductId(cart.getProductId()).setStock(productRepository.findByProductId(cart.getProductId()).getStock() - cart.getQuantity());
            cartRepository.save(cart1);
            return cart1;
        }
        cart.setPrice(productRepository.findByProductId(cart.getProductId()).getPrice() * (cart.getQuantity()));
        productRepository.findByProductId(cart.getProductId()).setStock(productRepository.findByProductId(cart.getProductId()).getStock() - cart.getQuantity());
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart addProductToCartImportProduct(Cart cart){
        if(importRepository.findByProductId(cart.getProductId()).getStock() < cart.getQuantity()){
            // return null if stock is not enough
            return null;
        }
        if(cartRepository.findByProductIdAndUsername(cart.getProductId(), cart.getUsername()) != null){
            // if product already in cart, increase quantity
            Cart cart1 = cartRepository.findByProductIdAndUsername(cart.getProductId(), cart.getUsername());
            cart1.setQuantity(cart1.getQuantity() + cart.getQuantity());
            cart1.setPrice( cart1.getPrice()+cart.getPrice());
            importRepository.findByProductId(cart.getProductId()).setStock(importRepository.findByProductId(cart.getProductId()).getStock() - cart.getQuantity());
            cartRepository.save(cart1);
            return cart1;
        }
        cart.setPrice(importRepository.findByProductId(cart.getProductId()).getPrice() * (cart.getQuantity()));
        importRepository.findByProductId(cart.getProductId()).setStock(importRepository.findByProductId(cart.getProductId()).getStock() - cart.getQuantity());
        cartRepository.save(cart);
        return cart;}

    @Override
    public void removeProductFromCart(long id, String productId) {
        if(cartRepository.findById(id) == null){
            return;
        }
        else{
            Cart cart = cartRepository.findById(id).orElse(null);
            productRepository.findByProductId(productId).setStock(productRepository.findByProductId(productId).getStock() + cart.getQuantity());
            cartRepository.deleteById(id);
        }
    }

    @Override
    public Cart updateProductQuantityInCart(long id, int quantity, String productId) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            if(productRepository.findByProductId(productId).getStock() < quantity){
                return cart;
            }
            cart.setQuantity(quantity);
            productRepository.findByProductId(productId).setStock(productRepository.findByProductId(productId).getStock() - quantity);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public int gettotalCart(String user) {
        int totalPrice = 0;
        List<Cart> userCart = cartRepository.findAllByUsername(user);
        for(Cart i : userCart){
            totalPrice += Double.valueOf(i.getPrice());
        }
        return totalPrice;
    }

    @Override
    public double getPriceCart(long id) {
        double totalPrice = 0;
        Cart userCart = cartRepository.findCartById(id);
        totalPrice = userCart.getPrice();
        return totalPrice;
    }
    @Override
    public List<Cart> findCartByUser(String user) {
        return cartRepository.findAllByUsername(user);
    }

    @Override
    public Cart decreaseProductQuantityInCart(long id, String productId){
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() - 1);
            productRepository.findByProductId(productId).setStock(productRepository.findByProductId(productId).getStock() + 1);
            cart.setPrice(cart.getPrice()- productRepository.findByProductId(productId).getPrice());
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Cart increaseProductQuantityInCart(long id, String productId) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            if (productRepository.findByProductId(productId).getStock() == 0) {
                return cart;
            }else {
                cart.setQuantity(cart.getQuantity() + 1);
                productRepository.findByProductId(productId).setStock(productRepository.findByProductId(productId).getStock() - 1);
                cart.setPrice(cart.getPrice()+(productRepository.findByProductId(productId).getPrice()));
            }
            cartRepository.save(cart);
        }
        return cart;
    }
}

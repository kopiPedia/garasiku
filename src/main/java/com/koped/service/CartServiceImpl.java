package com.koped.service;
import com.koped.model.Cart;
import com.koped.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService{
  private final CartRepository cartRepository;

    @Override
    public Cart addProductToCart(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void removeProductFromCart(long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart updateProductQuantityInCart(long id, int quantity) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public int gettotalCart(String user) {
        int totalItem = 0;
        List<Cart> userCart = cartRepository.findAllByUsername(user);
        for(Cart i : userCart){
            totalItem += i.getQuantity();
        }
        return totalItem;
    }

    @Override
    public int getPriceCart(String user) {
        int totalPrice = 0;
        List<Cart> userCart = cartRepository.findAllByUsername(user);
       for(Cart i : userCart){
             totalPrice += i.getQuantity() * i.getPrice();
        }
        return totalPrice;
    }
    @Override
    public List<Cart> findCartByUser(String user) {
        return cartRepository.findAllByUsername(user);
    }
}

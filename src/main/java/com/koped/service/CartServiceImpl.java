package com.koped.service;
import com.koped.model.Cart;
import com.koped.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

  private CartRepository cartRepository;

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
        List<Cart> userCart = cartRepository.findAllByUser(user);
        for(Cart i : userCart){
            totalItem += i.getQuantity();
        }
        return totalItem;
    }

    @Override
    public int getPriceCart(String user) {
        int totalPrice = 0;
        List<Cart> userCart = cartRepository.findAllByUser(user);
       for(Cart i : userCart){
             totalPrice += i.getQuantity() * i.getPrice();
        }
        return totalPrice;
    }
}

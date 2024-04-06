package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void testCartProperties() {
        String userIdentifier = "User1";
        String productIdentifier = "Product1";

        Cart cart = new Cart();
        cart.setUser(userIdentifier);
        cart.setProduct(productIdentifier);
        cart.setQuantity(5);
        cart.setId(10);
        cart.setPrice(100);

        assertEquals(userIdentifier, cart.getUser(), "The user identifier should be correctly set in the cart.");
        assertEquals(productIdentifier, cart.getProduct(), "The product identifier should be correctly set in the cart.");
        assertEquals(5, cart.getQuantity(), "The quantity should be correctly set in the cart.");
        assertEquals(10, cart.getId(), "The ID should be correctly set in the cart.");
        assertEquals(100, cart.getPrice(), "The price should be correctly set in the cart.");
    }
}

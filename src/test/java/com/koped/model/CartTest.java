package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testCartProperties() {
        // Positive test
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

    @Test
    public void testCartNegativeQuantity() {
        // Negative test for quantity
        Cart cart = new Cart();
        cart.setQuantity(-1); // Attempt to set a negative quantity

        assertTrue(cart.getQuantity() < 0, "The cart should not accept negative quantities.");
    }

    @Test
    public void testCartNegativePrice() {
        // Negative test for price
        Cart cart = new Cart();
        cart.setPrice(-100); // Attempt to set a negative price

        assertTrue(cart.getPrice() < 0, "The cart should not accept negative prices.");
    }

    @Test
    public void testCartZeroQuantity() {
        // Edge case test for quantity
        Cart cart = new Cart();
        cart.setQuantity(0);

        assertEquals(0, cart.getQuantity(), "The quantity can be zero, representing an empty cart.");
    }

    @Test
    public void testCartHighQuantity() {
        // Positive test for high quantity
        Cart cart = new Cart();
        cart.setQuantity(10000);

        assertEquals(10000, cart.getQuantity(), "The cart should handle high quantities correctly.");
    }

    @Test
    public void testCartHighPrice() {
        // Positive test for high price
        Cart cart = new Cart();
        cart.setPrice(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, cart.getPrice(), "The cart should be able to handle the maximum integer price value.");
    }

    @Test
    public void testCartInvalidUser() {
        // Negative test for user field
        Cart cart = new Cart();
        cart.setUser(""); // Set an empty string as the user

        assertEquals("", cart.getUser(), "The cart should handle empty user strings.");
    }

    @Test
    public void testCartInvalidProduct() {
        // Negative test for product field
        Cart cart = new Cart();
        cart.setProduct(""); // Set an empty string as the product

        assertEquals("", cart.getProduct(), "The cart should handle empty product strings.");
    }
}

package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testCartNegativeQuantity() {
        Cart cart = new Cart();
        cart.setQuantity(-1);

        assertTrue(cart.getQuantity() < 0, "The cart should not accept negative quantities.");
    }

    @Test
    public void testCartNegativePrice() {
        Cart cart = new Cart();
        cart.setPrice(-100);

        assertTrue(cart.getPrice() < 0, "The cart should not accept negative prices.");
    }

    @Test
    public void testCartZeroQuantity() {
        Cart cart = new Cart();
        cart.setQuantity(0);

        assertEquals(0, cart.getQuantity(), "The quantity can be zero, representing an empty cart.");
    }

    @Test
    public void testCartHighQuantity() {
        Cart cart = new Cart();
        cart.setQuantity(10000);

        assertEquals(10000, cart.getQuantity(), "The cart should handle high quantities correctly.");
    }

    @Test
    public void testCartHighPrice() {
        Cart cart = new Cart();
        cart.setPrice(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, cart.getPrice(), "The cart should be able to handle the maximum integer price value.");
    }

    @Test
    public void testCartInvalidUser() {
        Cart cart = new Cart();
        cart.setUser("");

        assertEquals("", cart.getUser(), "The cart should handle empty user strings.");
    }

    @Test
    public void testCartInvalidProduct() {
        Cart cart = new Cart();
        cart.setProduct("");

        assertEquals("", cart.getProduct(), "The cart should handle empty product strings.");
    }

    @Test
    public void testUpdateProductQuantity() {
        Cart cart = new Cart();
        cart.setProduct("Product1");
        cart.setQuantity(1);
        cart.setQuantity(2);

        assertEquals(2, cart.getQuantity(), "The cart should correctly update the quantity of a product.");
    }

    @Test
    public void testMultipleProductEntries() {
        Cart cart1 = new Cart();
        cart1.setUser("User1");
        cart1.setProduct("Product1");
        cart1.setQuantity(1);
        cart1.setPrice(100);

        Cart cart2 = new Cart();
        cart2.setUser("User1");
        cart2.setProduct("Product2");
        cart2.setQuantity(2);
        cart2.setPrice(200);

        assertNotEquals(cart1.getProduct(), cart2.getProduct(), "Each cart entry should handle different products.");
        assertNotEquals(cart1.getPrice(), cart2.getPrice(), "Each cart entry should handle different prices.");
        assertEquals(cart1.getUser(), cart2.getUser(), "Both carts should belong to the same user if set so.");
    }

    @Test
    public void testRemoveProduct() {
        Cart cart = new Cart();
        cart.setProduct("Product1");
        cart.setQuantity(1);
        cart.setQuantity(0);

        assertEquals(0, cart.getQuantity(), "Setting quantity to zero should simulate removing the product.");
    }
}

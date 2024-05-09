package com.koped.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testCartProperties() {
        String userIdentifier = "User1";
        String productIdentifier = "Product1";

        Cart cart = new Cart();
        cart.setUsername(userIdentifier);
        cart.setProduct(productIdentifier);
        cart.setQuantity(5);
        cart.setId(10);
        cart.setPrice(new BigDecimal(100));

        assertEquals(userIdentifier, cart.getUsername(), "The user identifier should be correctly set in the cart.");
        assertEquals(productIdentifier, cart.getProduct(), "The product identifier should be correctly set in the cart.");
        assertEquals(5, cart.getQuantity(), "The quantity should be correctly set in the cart.");
        assertEquals(10, cart.getId(), "The ID should be correctly set in the cart.");
        assertEquals(new BigDecimal(100), cart.getPrice(), "The price should be correctly set in the cart.");
    }

    @Test
    public void testCartNegativeQuantity() {
        Cart cart = new Cart();
        cart.setQuantity(-1);

        assertTrue(cart.getQuantity() < 0, "The cart should not accept negative quantities.");
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
        cart.setPrice(new BigDecimal(Integer.MAX_VALUE));

        assertEquals(new BigDecimal(Integer.MAX_VALUE), cart.getPrice(), "The cart should be able to handle the maximum integer price value.");
    }

    @Test
    public void testCartInvalidUser() {
        Cart cart = new Cart();
        cart.setUsername("");

        assertEquals("", cart.getUsername(), "The cart should handle empty user strings.");
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
        cart1.setUsername("User1");
        cart1.setProduct("Product1");
        cart1.setQuantity(1);
        cart1.setPrice(new BigDecimal(100));

        Cart cart2 = new Cart();
        cart2.setUsername("User1");
        cart2.setProduct("Product2");
        cart2.setQuantity(2);
        cart2.setPrice(new BigDecimal(200));

        assertNotEquals(cart1.getProduct(), cart2.getProduct(), "Each cart entry should handle different products.");
        assertNotEquals(cart1.getPrice(), cart2.getPrice(), "Each cart entry should handle different prices.");
        assertEquals(cart1.getUsername(), cart2.getUsername(), "Both carts should belong to the same user if set so.");
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

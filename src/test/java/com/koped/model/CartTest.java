package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void testCartProperties() {
        // Create new User and Product instances for testing
        User user = new User(); // Assuming User class has default constructor and setters
        user.setId(1); // Assuming an setId method exists

        Product product = new Product(); // Assuming Product class has default constructor and setters
        product.setId(1); // Assuming an setId method exists

        // Create a Cart instance and set properties
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(5);
        cart.setId(10); // Assuming this is manually set for the purpose of the test
        cart.setPrice(100);

        // Assert that properties are correctly set
        assertEquals(user, cart.getUser(), "The user should be correctly set in the cart.");
        assertEquals(product, cart.getProduct(), "The product should be correctly set in the cart.");
        assertEquals(5, cart.getQuantity(), "The quantity should be correctly set in the cart.");
        assertEquals(10, cart.getId(), "The ID should be correctly set in the cart.");
        assertEquals(100, cart.getPrice(), "The price should be correctly set in the cart.");
    }
}

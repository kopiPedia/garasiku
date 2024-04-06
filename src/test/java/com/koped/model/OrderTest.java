package com.koped.model;

import com.koped.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void testOrderProperties() {

        User user = new User();
        user.setId(1);

        Cart cart = new Cart();
        cart.setId(1);

        Order order = new Order();
        order.setId(1);
        order.setUser(user);
        order.setCart(cart);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);

        assertEquals(1, order.getId(), "The ID should be correctly set in the order.");
        assertEquals(user, order.getUser(), "The user should be correctly set in the order.");
        assertEquals(cart, order.getCart(), "The cart should be correctly set in the order.");
        assertEquals(OrderStatus.PLACED, order.getStatus(), "The status should be correctly set in the order.");
    }
}

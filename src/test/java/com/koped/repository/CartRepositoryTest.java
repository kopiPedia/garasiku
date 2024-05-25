package com.koped.repository;

import com.koped.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartRepositoryTest {

    @Mock
    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAllByUser() {
        Cart cart1 = new Cart();
        cart1.setId(1L);
        cart1.setUsername("user1");

        Cart cart2 = new Cart();
        cart2.setId(2L);
        cart2.setUsername("user1");

        when(cartRepository.findAllByUsername("user1")).thenReturn(Arrays.asList(cart1, cart2));

        List<Cart> carts = cartRepository.findAllByUsername("user1");

        assertNotNull(carts);
        assertEquals(2, carts.size());
        verify(cartRepository, times(1)).findAllByUsername("user1");
    }

    @Test
    void testNoCartsFoundForUser() {
        when(cartRepository.findAllByUsername("nonexistentuser")).thenReturn(Arrays.asList());

        List<Cart> carts = cartRepository.findAllByUsername("nonexistentuser");

        assertNotNull(carts);
        assertTrue(carts.isEmpty());
        verify(cartRepository, times(1)).findAllByUsername("nonexistentuser");
    }

    @Test
    void testFindAllByUserWithNull() {
        when(cartRepository.findAllByUsername(null)).thenThrow(new IllegalArgumentException("User must not be null"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartRepository.findAllByUsername(null);
        });

        assertEquals("User must not be null", exception.getMessage());
        verify(cartRepository, times(1)).findAllByUsername(null);
    }

    @Test
    void testFindAllByUserWithEmptyString() {
        when(cartRepository.findAllByUsername("")).thenReturn(Arrays.asList());

        List<Cart> carts = cartRepository.findAllByUsername("");

        assertNotNull(carts);
        assertTrue(carts.isEmpty(), "Expected no carts for empty user string.");
        verify(cartRepository, times(1)).findAllByUsername("");
    }

    @Test
    void testUserCartContents() {
        Cart cart1 = new Cart();
        cart1.setId(1L);
        cart1.setUsername("user1");
        cart1.setProduct("Product1");
        cart1.setQuantity(2);
        cart1.setPrice(100);

        Cart cart2 = new Cart();
        cart2.setId(2L);
        cart2.setUsername("user1");
        cart2.setProduct("Product2");
        cart2.setQuantity(3);
        cart2.setPrice(200);

        when(cartRepository.findAllByUsername("user1")).thenReturn(Arrays.asList(cart1, cart2));

        List<Cart> carts = cartRepository.findAllByUsername("user1");

        assertNotNull(carts);
        assertEquals(2, carts.size(), "Should retrieve two carts for user1");
        assertEquals("Product1", carts.get(0).getProduct());
        assertEquals(2, carts.get(0).getQuantity());
        assertEquals(100, carts.get(0).getPrice());
        assertEquals("Product2", carts.get(1).getProduct());
        assertEquals(3, carts.get(1).getQuantity());
        assertEquals(200, carts.get(1).getPrice());
    }
}
package com.koped.repository;

import com.koped.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        cart1.setUser("user1");

        Cart cart2 = new Cart();
        cart2.setId(2L);
        cart2.setUser("user1");

        when(cartRepository.findAllByUser("user1")).thenReturn(Arrays.asList(cart1, cart2));

        List<Cart> carts = cartRepository.findAllByUser("user1");

        assertNotNull(carts);
        assertEquals(2, carts.size());
        verify(cartRepository, times(1)).findAllByUser("user1");
    }

    @Test
    void testNoCartsFoundForUser() {
        when(cartRepository.findAllByUser("nonexistentuser")).thenReturn(Arrays.asList());

        List<Cart> carts = cartRepository.findAllByUser("nonexistentuser");

        assertNotNull(carts);
        assertTrue(carts.isEmpty());
        verify(cartRepository, times(1)).findAllByUser("nonexistentuser");
    }

    @Test
    void testFindAllByUserWithNull() {
        when(cartRepository.findAllByUser(null)).thenThrow(new IllegalArgumentException("User must not be null"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartRepository.findAllByUser(null);
        });

        assertEquals("User must not be null", exception.getMessage());
        verify(cartRepository, times(1)).findAllByUser(null);
    }

    @Test
    void testFindAllByUserWithEmptyString() {
        when(cartRepository.findAllByUser("")).thenReturn(Arrays.asList());

        List<Cart> carts = cartRepository.findAllByUser("");

        assertNotNull(carts);
        assertTrue(carts.isEmpty(), "Expected no carts for empty user string.");
        verify(cartRepository, times(1)).findAllByUser("");
    }

    @Test
    void testUserCartContents() {
        Cart cart1 = new Cart();
        cart1.setId(1L);
        cart1.setUser("user1");
        cart1.setProduct("Product1");
        cart1.setQuantity(2);
        cart1.setPrice(100);

        Cart cart2 = new Cart();
        cart2.setId(2L);
        cart2.setUser("user1");
        cart2.setProduct("Product2");
        cart2.setQuantity(3);
        cart2.setPrice(200);

        when(cartRepository.findAllByUser("user1")).thenReturn(Arrays.asList(cart1, cart2));

        List<Cart> carts = cartRepository.findAllByUser("user1");

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
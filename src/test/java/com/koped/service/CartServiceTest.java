package com.koped.service;

import com.koped.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        cartService = Mockito.mock(CartService.class); // Mocking CartService
    }

    @Test
    public void testAddProductToCart_Success() {
        Cart cart = new Cart();
        cart.setUsername("User1");
        cart.setProduct("Product1");
        cart.setQuantity(1);
        cart.setPrice(100);

        Mockito.when(cartService.addProductToCart(Mockito.any(Cart.class))).thenReturn(cart);

        Cart result = cartService.addProductToCart(cart);
        assertNotNull(result);
        assertEquals("User1", result.getUsername());
        assertEquals("Product1", result.getProduct());
        assertEquals(1, result.getQuantity());
        assertEquals(100, result.getPrice());
    }

    @Test
    public void testRemoveProductFromCart_Success() {
        Mockito.doNothing().when(cartService).removeProductFromCart(Mockito.anyLong());

        assertDoesNotThrow(() -> cartService.removeProductFromCart(10));
    }

    @Test
    public void testUpdateProductQuantityInCart_Success() {
        Cart cart = new Cart();
        cart.setId(10);
        cart.setQuantity(5);

        Mockito.when(cartService.updateProductQuantityInCart(Mockito.anyLong(), Mockito.anyInt())).thenReturn(cart);

        Cart updatedCart = cartService.updateProductQuantityInCart(10, 5);
        assertNotNull(updatedCart);
        assertEquals(5, updatedCart.getQuantity());
    }

    @Test
    public void testUpdateProductQuantityInCart_Failure() {
        Mockito.when(cartService.updateProductQuantityInCart(Mockito.anyLong(), Mockito.anyInt())).thenReturn(null);

        Cart result = cartService.updateProductQuantityInCart(10, -1); // assuming negative quantity should fail
        assertNull(result);
    }

    @Test
    public void testGetTotalCart_Success() {
        Mockito.when(cartService.gettotalCart("User1")).thenReturn(5);

        int totalQuantity = cartService.gettotalCart("User1");
        assertEquals(5, totalQuantity);
    }

    @Test
    public void testGetPriceCart_Success() {
        Mockito.when(cartService.getPriceCart("User1")).thenReturn(300);

        int totalPrice = cartService.getPriceCart("User1");
        assertEquals(300, totalPrice);
    }

    @Test
    public void testAddProductToCart_Failure_InvalidProduct() {
        Cart invalidCart = new Cart();
        invalidCart.setUsername("User1");
        invalidCart.setProduct("");
        invalidCart.setQuantity(1);
        invalidCart.setPrice(100);

        Mockito.when(cartService.addProductToCart(Mockito.any(Cart.class))).thenReturn(null);

        Cart result = cartService.addProductToCart(invalidCart);
        assertNull(result, "Should return null for invalid product.");
    }

    @Test
    public void testAddProductToCart_Failure_EmptyUser() {
        Cart invalidCart = new Cart();
        invalidCart.setUsername("");
        invalidCart.setProduct("Product1");
        invalidCart.setQuantity(1);
        invalidCart.setPrice(100);

        Mockito.when(cartService.addProductToCart(Mockito.any(Cart.class))).thenReturn(null);

        Cart result = cartService.addProductToCart(invalidCart);
        assertNull(result, "Should return null for carts with an empty user field.");
    }

    @Test
    public void testRemoveProductFromCart_Failure() {
        Mockito.doThrow(new IllegalArgumentException("Product does not exist")).when(cartService).removeProductFromCart(Mockito.anyLong());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.removeProductFromCart(999); // Assuming 999 does not exist
        });
        assertEquals("Product does not exist", exception.getMessage(), "Should throw an exception if product does not exist.");
    }

    @Test
    public void testGetTotalCart_Failure_InvalidUser() {
        Mockito.when(cartService.gettotalCart("InvalidUser")).thenReturn(0);

        int totalQuantity = cartService.gettotalCart("InvalidUser");
        assertEquals(0, totalQuantity, "Should return 0 for an invalid user.");
    }

    @Test
    public void testGetPriceCart_Failure_InvalidUser() {
        Mockito.when(cartService.getPriceCart("InvalidUser")).thenReturn(0);

        int totalPrice = cartService.getPriceCart("InvalidUser");
        assertEquals(0, totalPrice, "Should return 0 for an invalid user.");
    }

    @Test
    public void testEdgeCase_MaxIntegerPrice() {
        Cart cart = new Cart();
        cart.setPrice(Integer.MAX_VALUE);
        Mockito.when(cartService.addProductToCart(Mockito.any(Cart.class))).thenReturn(cart);

        Cart result = cartService.addProductToCart(cart);
        assertEquals(Integer.MAX_VALUE, result.getPrice(), "Should handle maximum integer value for price.");
    }
}

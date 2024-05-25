/* package com.koped.service;

import com.koped.model.Cart;
import com.koped.model.Product;
import com.koped.repository.CartRepository;
import com.koped.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductToCart_StockSufficient() {
        Cart cart = new Cart();
        cart.setProductId("123");
        cart.setQuantity(1);
        cart.setUsername("user1");

        Product product = new Product();
        product.setProductId("123");
        product.setStock(10);
        product.setPrice(new BigDecimal("100.00"));

        when(productRepository.findByProductId("123")).thenReturn(product);
        when(cartRepository.findByProductIdAndUsername("123", "user1")).thenReturn(null);

        Cart result = cartService.addProductToCart(cart);

        assertNotNull(result);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testFindCartByUser() {
        List<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart());
        cartList.add(new Cart());

        when(cartRepository.findAllByUsername("user1")).thenReturn(cartList);

        List<Cart> foundCarts = cartService.findCartByUser("user1");

        assertEquals(2, foundCarts.size());

    }

    @Test
    void testAddProductToCart_StockInsufficient() {
        Cart cart = new Cart();
        cart.setProductId("123");
        cart.setQuantity(15); // Assuming there are only 10 items in stock
        cart.setUsername("user1");

        Product product = new Product();
        product.setProductId("123");
        product.setStock(10); // Available stock
        product.setPrice(new BigDecimal("100.00"));

        when(productRepository.findByProductId("123")).thenReturn(product);

        Cart result = cartService.addProductToCart(cart);

        assertNull(result); // Expecting null as product couldn't be added due to insufficient stock
        verify(cartRepository, never()).save(any(Cart.class)); // Ensure cart is not saved
    }

    @Test
    void testUpdateProductQuantityInCart_CartNotFound() {
        long cartId = 1L;
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        Cart result = cartService.updateProductQuantityInCart(cartId, 5, "123");

        assertNull(result);
    }

    @Test
    void testIncreaseProductQuantityInCart_NoStockAvailable() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProductId("123");
        cart.setQuantity(1);

        Product product = new Product();
        product.setStock(0);

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(productRepository.findByProductId("123")).thenReturn(product);

        Cart result = cartService.increaseProductQuantityInCart(1L, "123");

        assertNotNull(result);
        assertEquals(1, result.getQuantity()); // Quantity remains unchanged
    }

    @Test
    void testGetPriceCart_EmptyCart() {
        when(cartRepository.findAllByUsername("user1")).thenReturn(new ArrayList<>());

        BigDecimal result = cartService.getPriceCart("user1");

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void testUpdateProductQuantityInCart_CartDoesNotExist() {
        long cartId = 1L;
        String productId = "123";
        int quantity = 5;

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        Cart result = cartService.updateProductQuantityInCart(cartId, quantity, productId);

        verify(productRepository, never()).findByProductId(anyString());
        verify(cartRepository, never()).save(any(Cart.class));

        assertNull(result, "Cart should be null because it does not exist");
    }

    @Test
    void testGetTotalCart_EmptyCart() {
        String username = "user1";
        when(cartRepository.findAllByUsername(username)).thenReturn(new ArrayList<>());

        int total = cartService.gettotalCart(username);

        assertEquals(0, total);
    }

    @Test
    void testIncreaseProductQuantityInCart_NoStockAvailablee() {
        long cartId = 1L;
        String productId = "123";
        Cart cart = new Cart();
        cart.setId(cartId);
        cart.setProductId(productId);
        cart.setQuantity(1);

        Product product = new Product();
        product.setStock(0);  // No stock available

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(productRepository.findByProductId(productId)).thenReturn(product);

        Cart result = cartService.increaseProductQuantityInCart(cartId, productId);

        assertNotNull(result);
        assertEquals(1, result.getQuantity()); // Quantity remains unchanged
        verify(cartRepository, never()).save(cart);
    }

} */

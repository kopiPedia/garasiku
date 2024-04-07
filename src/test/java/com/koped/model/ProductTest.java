package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void testPositiveScenarios() {
        
        Product product = new Product();
        assertNotNull(product);

        product.setId(1);
        assertEquals(1, product.getId());
        
        product.setTitle("Test Product");
        assertEquals("Test Product", product.getTitle());
        
        product.setDescription("This is a test product.");
        assertEquals("This is a test product.", product.getDescription());
        
        product.setPrice(new BigDecimal("10.50"));
        assertEquals(new BigDecimal("10.50"), product.getPrice());
        
        product.setCategory("Electronics");
        assertEquals("Electronics", product.getCategory());
        
        product.setCountry("IDN");
        assertEquals("IDN", product.getCountry());
        
        product.setStock(100);
        assertEquals(Integer.valueOf(100), product.getStock());
        
        product.setImage("test_image.jpg");
        assertEquals("test_image.jpg", product.getImage());
        
        product.setProductId("ABC123");
        assertEquals("ABC123", product.getProductId());
    }

    @Test
    public void testNegativeScenarios() {
        
        Product product = new Product();
        assertNull(product.getTitle());
        assertNull(product.getDescription());
        assertNull(product.getPrice());
        assertNull(product.getCategory());
        assertNull(product.getCountry());
        assertNull(product.getStock());
        assertNull(product.getImage());
        assertNull(product.getProductId());

      
        assertThrows(IllegalArgumentException.class, () -> product.setId(-1));
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(new BigDecimal("-10.50")));
        assertThrows(IllegalArgumentException.class, () -> product.setStock(-100));
    }
}


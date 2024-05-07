package com.koped.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void testPositiveScenarios() {
        
        Product product = new Product();
        Assertions.assertNotNull(product);

        product.setId(1);
        Assertions.assertEquals(1, product.getId());
        
        product.setTitle("Test Product");
        Assertions.assertEquals("Test Product", product.getTitle());
        
        product.setDescription("This is a test product.");
        Assertions.assertEquals("This is a test product.", product.getDescription());
        
        product.setPrice(new BigDecimal("10.50"));
        Assertions.assertEquals(new BigDecimal("10.50"), product.getPrice());
        
        product.setCategory("Electronics");
        Assertions.assertEquals("Electronics", product.getCategory());
        
        product.setCountry("IDN");
        Assertions.assertEquals("IDN", product.getCountry());
        
        product.setStock(100);
        Assertions.assertEquals(Integer.valueOf(100), product.getStock());
        
        product.setImage("test_image.jpg");
        Assertions.assertEquals("test_image.jpg", product.getImage());
        
        product.setProductId("ABC123");
        Assertions.assertEquals("ABC123", product.getProductId());
    }

    @Test
    public void testNegativeScenarios() {
        
        Product product = new Product();
        Assertions.assertNull(product.getTitle());
        Assertions.assertNull(product.getDescription());
        Assertions.assertNull(product.getPrice());
        Assertions.assertNull(product.getCategory());
        Assertions.assertNull(product.getCountry());
        Assertions.assertNull(product.getStock());
        Assertions.assertNull(product.getImage());
        Assertions.assertNull(product.getProductId());

      
        Assertions.assertThrows(IllegalArgumentException.class, () -> product.setId(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> product.setPrice(new BigDecimal("-10.50")));
        Assertions.assertThrows(IllegalArgumentException.class, () -> product.setStock(-100));
    }
}


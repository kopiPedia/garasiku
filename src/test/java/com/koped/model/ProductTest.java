package com.koped.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testPositiveScenarios() {
        
        Product product = new Product();
        Assertions.assertNotNull(product);

        product.setId(1);
        assertEquals(1, product.getId());
        
        product.setTitle("Test Product");
        assertEquals("Test Product", product.getTitle());
        
        product.setDescription("This is a test product.");
        assertEquals("This is a test product.", product.getDescription());
        
        product.setPrice(10.50);
        assertEquals(10.50, product.getPrice());
        
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
        Assertions.assertNull(product.getTitle());
        Assertions.assertNull(product.getDescription());
        Assertions.assertNull(product.getPrice());
        Assertions.assertNull(product.getCategory());
        Assertions.assertNull(product.getCountry());
        Assertions.assertNull(product.getStock());
        Assertions.assertNull(product.getImage());
        Assertions.assertNull(product.getProductId());

      
        Assertions.assertThrows(IllegalArgumentException.class, () -> product.setId(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> product.setPrice(-10.50));
        Assertions.assertThrows(IllegalArgumentException.class, () -> product.setStock(-100));
    }

    @Test
    public void testSetIdWithZero() {
        Product product = new Product();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            product.setId(0);
        });
        assertEquals("Id must be > 0", exception.getMessage());
    }

    @Test
    public void testSetPriceWithNull() {
        Product product = new Product();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            product.setPrice(null);
        });
        assertEquals("Price must be >= 0", exception.getMessage());
    }

    @Test
    public void testSetPriceWithZero() {
        Product product = new Product();
        product.setPrice(0.0);
        assertEquals(BigDecimal.ZERO, product.getPrice());
    }

    @Test
    public void testSetStockWithNull() {
        Product product = new Product();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            product.setStock(null);
        });
        assertEquals("Stock cannot be null or negative", exception.getMessage());
    }

    @Test
    public void testSetStockWithZero() {
        Product product = new Product();
        product.setStock(0);
        assertEquals(Integer.valueOf(0), product.getStock());
    }

    @Test
    public void testProductConstructor() {
        String title = "Laptop";
        String description = "High-performance laptop";
        Double price = 999.99;
        String category = "Electronics";
        String country = "USA";
        Integer stock = 25;
        String image = "laptop.jpg";
        String productId = "LP12345";

        Product product = new Product(title, description, price, category, country, stock, image, productId);

        assertEquals(title, product.getTitle(), "Title should match the constructor input");
        assertEquals(description, product.getDescription(), "Description should match the constructor input");
        assertEquals(0, price.compareTo(product.getPrice()), "Price should match the constructor input");
        assertEquals(category, product.getCategory(), "Category should match the constructor input");
        assertEquals(country, product.getCountry(), "Country should match the constructor input");
        assertEquals(stock, product.getStock(), "Stock should match the constructor input");
        assertEquals(image, product.getImage(), "Image should match the constructor input");
        assertEquals(productId, product.getProductId(), "Product ID should match the constructor input");
    }

}


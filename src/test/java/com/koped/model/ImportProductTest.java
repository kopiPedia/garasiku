package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class ImportProductTest {

    @Test
    public void testSetProductId_ValidProductId() {
        ImportProduct product = new ImportProduct();
        String productId = "12345";
        product.setProductId(productId);
        assertEquals(productId, product.getProductId(), "Product ID should be correctly set.");
    }

    @Test
    public void testSetProductId_NullOrEmpty() {
        ImportProduct product = new ImportProduct();
        Exception nullEx = assertThrows(IllegalArgumentException.class, () -> product.setProductId(null));
        assertEquals("Product ID cannot be null or empty", nullEx.getMessage());

        Exception emptyEx = assertThrows(IllegalArgumentException.class, () -> product.setProductId(""));
        assertEquals("Product ID cannot be null or empty", emptyEx.getMessage());
    }

    @Test
    public void testSetTitle_ValidTitle() {
        ImportProduct product = new ImportProduct();
        String title = "Laptop";
        product.setTitle(title);
        assertEquals(title, product.getTitle(), "Title should be correctly set.");
    }

    @Test
    public void testSetTitle_NullOrEmpty() {
        ImportProduct product = new ImportProduct();
        Exception nullEx = assertThrows(IllegalArgumentException.class, () -> product.setTitle(null));
        assertEquals("Title cannot be null or empty", nullEx.getMessage());

        Exception emptyEx = assertThrows(IllegalArgumentException.class, () -> product.setTitle(""));
        assertEquals("Title cannot be null or empty", emptyEx.getMessage());
    }

    @Test
    public void testSetCategory_ValidCategory() {
        ImportProduct product = new ImportProduct();
        String category = "Electronics";
        product.setCategory(category);
        assertEquals(category, product.getCategory(), "Category should be correctly set.");
    }

    @Test
    public void testSetCategory_NullOrEmpty() {
        ImportProduct product = new ImportProduct();
        Exception nullEx = assertThrows(IllegalArgumentException.class, () -> product.setCategory(null));
        assertEquals("Category cannot be null or empty", nullEx.getMessage());

        Exception emptyEx = assertThrows(IllegalArgumentException.class, () -> product.setCategory(""));
        assertEquals("Category cannot be null or empty", emptyEx.getMessage());
    }

    @Test
    public void testSetId() {
        ImportProduct product = new ImportProduct();
        product.setId(1);
        assertEquals(1, product.getId(), "Id should be correctly set.");
    }

    @Test
    public void testSetRequestId() {
        ImportProduct product = new ImportProduct();
        String requestId = "REQ123456";
        product.setRequestId(requestId);
        assertEquals(requestId, product.getRequestId(), "RequestId should be correctly set.");
    }

    @Test
    public void testSetDescription() {
        ImportProduct product = new ImportProduct();
        String description = "Detailed description of the product.";
        product.setDescription(description);
        assertEquals(description, product.getDescription(), "Description should be correctly set.");
    }

    @Test
    public void testSetStock() {
        ImportProduct product = new ImportProduct();
        Integer stock = 100;
        product.setStock(stock);
        assertEquals(stock, product.getStock(), "Stock should be correctly set.");
    }

    @Test
    public void testSetCountry() {
        ImportProduct product = new ImportProduct();
        String country = "USA";
        product.setCountry(country);
        assertEquals(country, product.getCountry(), "Country should be correctly set.");
    }

    @Test
    public void testSetPrice() {
        ImportProduct product = new ImportProduct();
        BigDecimal price = new BigDecimal("199.99");
        product.setPrice(price);
        assertEquals(0, price.compareTo(product.getPrice()), "Price should be correctly set.");
    }

    @Test
    public void testSetImage() {
        ImportProduct product = new ImportProduct();
        String image = "image.jpg";
        product.setImage(image);
        assertEquals(image, product.getImage(), "Image should be correctly set.");
    }

}

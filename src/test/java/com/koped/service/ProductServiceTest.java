package com.koped.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.koped.model.Product;
import com.koped.repository.ProductRepository;


public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByProductId() {
        // Given
        String productId = "ABC123";
        Product mockProduct = new Product();
        mockProduct.setProductId(productId);
        when(productRepository.findByProductId(productId)).thenReturn(mockProduct);

        // When
        Product result = productService.findByProductIds(productId);

        // Then
        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        verify(productRepository, times(1)).findByProductId(productId);
    }

    @Test
    public void testFindAllProducts() {
        // Given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> result = productService.findAllProducts();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

//    @Test
//    public void testDeleteByProductId() {
//        // Given
//        String productId = "ABC123";
//        when(productRepository.deleteByProductId(productId)).thenReturn("Deleted");
//
//        // When
//        String result = productService.deleteByProductId(productId);
//
//        // Then
//        assertNotNull(result);
//        assertEquals("Deleted", result);
//        verify(productRepository, times(1)).deleteByProductId(productId);
//    }

    @Test
    public void testUpdateByProductId() {
        // Given
        Product productToUpdate = new Product();
        productToUpdate.setId(1);
        productToUpdate.setTitle("Test Product");
        when(productRepository.save(productToUpdate)).thenReturn(productToUpdate);

        // When
        Product result = productService.updateByProductIds(productToUpdate);

        // Then
        assertNotNull(result);
        assertEquals("Test Product", result.getTitle());
        verify(productRepository, times(1)).save(productToUpdate);
    }

//    @Test
//    public void testCreateNewProduct() {
//        // Given
//        Product newProduct = new Product("New Product", "Description", 10.0, "Category", 10, "image.jpg", "NEW123");
//        when(productRepository.save(newProduct)).thenReturn(newProduct);
//
//        // When
//        Product result = productService.createNewProduct(newProduct, );
//
//        // Then
//        assertNotNull(result);
//        assertEquals("New Product", result.getTitle());
//        verify(productRepository, times(1)).save(newProduct);
//    }

    @Test
    public void testFindAllProducts_EmptyList() {
        // Given
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Product> result = productService.findAllProducts();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }
    @Test
    public void testFindByProductId_WithNullId() {
        // Given
        String productId = null;
        when(productRepository.findByProductId(productId)).thenReturn(null);

        // When
        Product result = productService.findByProductIds(productId);

        // Then
        assertNull(result);
        verify(productRepository, times(1)).findByProductId(productId);
    }

    @Test
    public void testFindAllProducts_ThrowsException() {
        when(productRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        Exception exception = null;

        try {
            productService.findAllProducts();
        } catch (Exception ex) {
            exception = ex;
        }

        // Then
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("Database error", exception.getMessage());
        verify(productRepository, times(1)).findAll();
    }

}

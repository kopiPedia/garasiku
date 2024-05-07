package com.koped.service;

import com.koped.model.ImportProduct;
import com.koped.repository.ImportRepository;
import com.koped.service.ImportProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ImportProductServiceImplTest {

    @Mock
    private ImportRepository importRepository;

    private ImportProductServiceImpl importProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        importProductService = new ImportProductServiceImpl(importRepository);
    }

    @Test
    void testFindByProductIds() {
        // Arrange
        String productId = "testProductId";
        ImportProduct expectedProduct = new ImportProduct();
        expectedProduct.setProductId(productId);
        when(importRepository.findByProductId(productId)).thenReturn(expectedProduct);

        // Act
        ImportProduct actualProduct = importProductService.findByProductIds(productId);

        // Assert
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        List<ImportProduct> expectedProducts = new ArrayList<>();
        expectedProducts.add(new ImportProduct());
        when(importRepository.findAll()).thenReturn(expectedProducts);

        // Act
        List<ImportProduct> actualProducts = importProductService.findAllProducts();

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void testDeleteByProductId() {
        // Arrange
        String productId = "testProductId";
        when(importRepository.deleteByProductId(productId)).thenReturn("Deleted");

        // Act
        String result = importProductService.deleteByProductId(productId);

        // Assert
        assertEquals("Deleted", result);
        verify(importRepository, times(1)).deleteByProductId(productId);
    }

    @Test
    void testUpdateByProductIds() {
        // Arrange
        ImportProduct productToUpdate = new ImportProduct();
        productToUpdate.setProductId("testProductId");
        when(importRepository.save(productToUpdate)).thenReturn(productToUpdate);

        // Act
        ImportProduct updatedProduct = importProductService.updateByProductIds(productToUpdate);

        // Assert
        assertEquals(productToUpdate, updatedProduct);
    }

    @Test
    void testCreateNewProduct() {
        // Arrange
        ImportProduct newProduct = new ImportProduct();
        newProduct.setProductId("testProductId");
        when(importRepository.save(newProduct)).thenReturn(newProduct);

        // Act
        ImportProduct createdProduct = importProductService.createNewProduct(newProduct);

        // Assert
        assertEquals(newProduct, createdProduct);
    }

    @Test
    void testFindByUserId() {
        // Arrange
        int userId = 1;
        ImportProduct expectedProduct = new ImportProduct();
        expectedProduct.setUserId(userId);
        when(importRepository.findByUserId(userId)).thenReturn(expectedProduct);

        // Act
        ImportProduct actualProduct = importProductService.findByUserId(userId);

        // Assert
        assertEquals(expectedProduct, actualProduct);
    }
}

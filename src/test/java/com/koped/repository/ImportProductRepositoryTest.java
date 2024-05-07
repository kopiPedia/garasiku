package com.koped.repository;

import com.koped.model.ImportProduct;
import com.koped.repository.ImportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImportProductRepositoryTest {

    @Mock
    private ImportRepository importRepository;

    @BeforeEach
    void setUp() {
        // Reset mock before each test
        reset(importRepository);
    }

    @Test
    void testFindByProductId_Exists() {
        // Arrange
        String existingProductId = "existingProductId";
        ImportProduct expectedProduct = new ImportProduct();
        expectedProduct.setProductId(existingProductId);
        when(importRepository.findByProductId(existingProductId)).thenReturn(expectedProduct);

        // Act
        ImportProduct actualProduct = importRepository.findByProductId(existingProductId);

        // Assert
        assertNotNull(actualProduct);
        assertEquals(existingProductId, actualProduct.getProductId());
    }

    @Test
    void testFindByProductId_NotExists() {
        // Arrange
        String nonExistingProductId = "nonExistingProductId";
        when(importRepository.findByProductId(nonExistingProductId)).thenReturn(null);

        // Act
        ImportProduct actualProduct = importRepository.findByProductId(nonExistingProductId);

        // Assert
        assertNull(actualProduct);
    }

    @Test
    void testDeleteByProductId_Exists() {
        // Arrange
        String existingProductId = "existingProductId";
        when(importRepository.deleteByProductId(existingProductId)).thenReturn("Deleted");

        // Act
        String result = importRepository.deleteByProductId(existingProductId);

        // Assert
        assertEquals("Deleted", result);
        verify(importRepository, times(1)).deleteByProductId(existingProductId);
    }

    @Test
    void testDeleteByProductId_NotExists() {
        // Arrange
        String nonExistingProductId = "nonExistingProductId";
        when(importRepository.deleteByProductId(nonExistingProductId)).thenReturn(null);

        // Act
        String result = importRepository.deleteByProductId(nonExistingProductId);

        // Assert
        assertNull(result);
        verify(importRepository, times(1)).deleteByProductId(nonExistingProductId);
    }

    @Test
    void testFindByUserId_Exists() {
        // Arrange
        int userId = 1;
        ImportProduct expectedProduct = new ImportProduct();
        expectedProduct.setUserId(userId);
        when(importRepository.findByUserId(userId)).thenReturn(expectedProduct);

        // Act
        ImportProduct actualProduct = importRepository.findByUserId(userId);

        // Assert
        assertNotNull(actualProduct);
        assertEquals(userId, actualProduct.getUserId());
    }

    @Test
    void testFindByUserId_NotExists() {
        // Arrange
        int nonExistingUserId = 100;
        when(importRepository.findByUserId(nonExistingUserId)).thenReturn(null);

        // Act
        ImportProduct actualProduct = importRepository.findByUserId(nonExistingUserId);

        // Assert
        assertNull(actualProduct);
    }
}

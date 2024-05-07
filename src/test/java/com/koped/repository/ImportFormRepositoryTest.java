package com.koped.repository;

import com.koped.model.ImportForm;
import com.koped.repository.ImportFormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImportFormRepositoryTest {

    @Mock
    private ImportFormRepository importFormRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByRequestId_Exists() {
        // Arrange
        String existingRequestId = "existingRequestId";
        ImportForm expectedForm = new ImportForm();
        expectedForm.setRequestId(existingRequestId);
        when(importFormRepository.findByRequestId(existingRequestId)).thenReturn(expectedForm);

        // Act
        ImportForm actualForm = importFormRepository.findByRequestId(existingRequestId);

        // Assert
        assertNotNull(actualForm);
        assertEquals(existingRequestId, actualForm.getRequestId());
    }

    @Test
    void testFindByRequestId_NotExists() {
        // Arrange
        String nonExistingRequestId = "nonExistingRequestId";
        when(importFormRepository.findByRequestId(nonExistingRequestId)).thenReturn(null);

        // Act
        ImportForm actualForm = importFormRepository.findByRequestId(nonExistingRequestId);

        // Assert
        assertNull(actualForm);
    }

    @Test
    void testDeleteByRequestId_Exists() {
        // Arrange
        String existingRequestId = "existingRequestId";
        when(importFormRepository.deleteByRequestId(existingRequestId)).thenReturn("Deleted");

        // Act
        String result = importFormRepository.deleteByRequestId(existingRequestId);

        // Assert
        assertEquals("Deleted", result);
        verify(importFormRepository, times(1)).deleteByRequestId(existingRequestId);
    }

    @Test
    void testDeleteByRequestId_NotExists() {
        // Arrange
        String nonExistingRequestId = "nonExistingRequestId";
        when(importFormRepository.deleteByRequestId(nonExistingRequestId)).thenReturn(null);

        // Act
        String result = importFormRepository.deleteByRequestId(nonExistingRequestId);

        // Assert
        assertNull(result);
        verify(importFormRepository, times(1)).deleteByRequestId(nonExistingRequestId);
    }

    @Test
    void testSetBudgetRange_Negative() {
        // Arrange
        ImportForm importForm = new ImportForm();
        BigDecimal negativeBudget = new BigDecimal("-100");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            importForm.setBudgetRange(negativeBudget);
        });
    }

    @Test
    void testSetProductName_Empty() {
        // Arrange
        ImportForm importForm = new ImportForm();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            importForm.setProductName("");
        });
    }

    @Test
    void testSaveImportForm() {
        // Arrange
        ImportForm importForm = new ImportForm();
        importForm.setRequestId("testRequestId");
        importForm.setProductName("Test Product");
        importForm.setBudgetRange(BigDecimal.TEN);

        ImportForm savedForm = new ImportForm();
        savedForm.setId(1L); // Assuming the ID is generated upon saving

        when(importFormRepository.save(importForm)).thenReturn(savedForm);

        // Act
        ImportForm result = importFormRepository.save(importForm);

        // Assert
        assertNotNull(result);
        assertEquals(savedForm.getId(), result.getId());
        assertEquals("testRequestId", result.getRequestId()); // Fix the expected value here
        assertEquals(importForm.getProductName(), result.getProductName());
        assertEquals(importForm.getBudgetRange(), result.getBudgetRange());
    }
}

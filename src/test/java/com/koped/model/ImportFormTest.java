package com.koped.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ImportFormTest {
    @Test
    public void testGetId() {
        ImportForm importForm = new ImportForm();
        importForm.setId(1L);
        assertEquals(1L, importForm.getId());
    }

    @Test
    public void testToString() {
        ImportForm importForm = new ImportForm();
        importForm.setId(1L);
        importForm.setUserId(1L);
        importForm.setProductName("productName");
        importForm.setExpectedCountry("expectedCountry");
        importForm.setDetails("details");
        importForm.setBudgetRange(BigDecimal.valueOf(1.0));
        importForm.setStatus("status");

        assertTrue(importForm.toString().contains("1"));
    }
    @Test
    public void testGetUserId() {
        ImportForm importForm = new ImportForm();
        importForm.setUserId(1L);
        assertEquals(1L, importForm.getUserId());
    }

    @Test
    public void testGetProductName() {
        ImportForm importForm = new ImportForm();
        importForm.setProductName("productName");
        assertEquals("productName", importForm.getProductName());
    }

    @Test
    public void testGetExpectedCountry() {
        ImportForm importForm = new ImportForm();
        importForm.setExpectedCountry("expectedCountry");
        assertEquals("expectedCountry", importForm.getExpectedCountry());
    }

    @Test
    public void testGetDetails() {
        ImportForm importForm = new ImportForm();
        importForm.setDetails("details");
        assertEquals("details", importForm.getDetails());
    }

    @Test
    public void testGetBudgetRange() {
        ImportForm importForm = new ImportForm();
        BigDecimal expectedBudgetRange = BigDecimal.valueOf(1.0);
        importForm.setBudgetRange(expectedBudgetRange);
        assertEquals(expectedBudgetRange, importForm.getBudgetRange());
    }
    @Test
    public void testGetStatus() {
        ImportForm importForm = new ImportForm();
        importForm.setStatus("status");
        assertEquals("status", importForm.getStatus());
    }

    @Test
    public void testCreateImportForm() {
        ImportForm importForm = new ImportForm();
        importForm.setId(1L);
        importForm.setUserId(1L);
        importForm.setProductName("productName");
        importForm.setExpectedCountry("expectedCountry");
        importForm.setDetails("details");
        importForm.setBudgetRange(BigDecimal.valueOf(1.0));
        importForm.setStatus("status");

        assertEquals(1L, importForm.getId());
        assertEquals(1L, importForm.getUserId());
        assertEquals("productName", importForm.getProductName());
        assertEquals("expectedCountry", importForm.getExpectedCountry());
        assertEquals("details", importForm.getDetails());
        assertEquals(BigDecimal.valueOf(1.0), importForm.getBudgetRange());
        assertEquals("status", importForm.getStatus());
    }
    @Test
    public void testImportFormDatabase() {
        ImportForm importForm = new ImportForm();
        importForm.setId(1L);
        importForm.setUserId(1L);
        importForm.setProductName("productName");
        importForm.setExpectedCountry("expectedCountry");
        importForm.setDetails("details");
        importForm.setBudgetRange(BigDecimal.valueOf(1.0));
        importForm.setStatus("status");

        // Validate all attributes are set correctly
        assertEquals(1L, importForm.getId());
        assertEquals(1L, importForm.getUserId());
        assertEquals("productName", importForm.getProductName());
        assertEquals("expectedCountry", importForm.getExpectedCountry());
        assertEquals("details", importForm.getDetails());
        assertEquals(BigDecimal.valueOf(1.0), importForm.getBudgetRange());
        assertEquals("status", importForm.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        ImportForm importForm = new ImportForm();
        importForm.setId(1L);
        importForm.setUserId(1L);
        importForm.setProductName("productName");
        importForm.setExpectedCountry("expectedCountry");
        importForm.setDetails("details");
        importForm.setBudgetRange(BigDecimal.valueOf(1.0));
        importForm.setStatus("status");

        assertEquals(1L, importForm.getId());
        assertEquals(1L, importForm.getUserId());
        assertEquals("productName", importForm.getProductName());
        assertEquals("expectedCountry", importForm.getExpectedCountry());
        assertEquals("details", importForm.getDetails());
        assertEquals(BigDecimal.valueOf(1.0), importForm.getBudgetRange());
        assertEquals("status", importForm.getStatus());
    }
    @Test
    public void testNegativeBudgetRange() {
        ImportForm importForm = new ImportForm();
        assertThrows(IllegalArgumentException.class, () -> importForm.setBudgetRange(BigDecimal.valueOf(-1.0)));
    }

    @Test
    public void testEmptyProductName() {
        ImportForm importForm = new ImportForm();
        assertThrows(IllegalArgumentException.class, () -> importForm.setProductName(""));
    }

    @Test
    public void testNullProductName() {
        ImportForm importForm = new ImportForm();
        assertThrows(IllegalArgumentException.class, () -> importForm.setProductName(null));
    }

    @Test
    public void testLargeBudgetRange() {
        ImportForm importForm = new ImportForm();
        BigDecimal expectedBudgetRange = BigDecimal.valueOf(Double.MAX_VALUE);
        importForm.setBudgetRange(expectedBudgetRange);
        assertEquals(expectedBudgetRange, importForm.getBudgetRange());
    }

    @Test
    public void testZeroBudgetRange() {
        ImportForm importForm = new ImportForm();
        BigDecimal expectedBudgetRange = BigDecimal.ZERO;
        importForm.setBudgetRange(expectedBudgetRange);
        assertEquals(expectedBudgetRange, importForm.getBudgetRange());
    }
}


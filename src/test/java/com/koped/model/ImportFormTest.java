package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class ImportFormTest {

    @Test
    public void testSetBudgetRange_Positive() {
        ImportForm form = new ImportForm();
        BigDecimal positiveRange = new BigDecimal("5000.00");
        form.setBudgetRange(positiveRange);
        assertEquals(positiveRange, form.getBudgetRange(), "Budget range should be correctly set.");
    }

    @Test
    public void testSetBudgetRange_Negative() {
        ImportForm form = new ImportForm();
        BigDecimal negativeRange = new BigDecimal("-1");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> form.setBudgetRange(negativeRange));
        assertEquals("Budget range cannot be negative", exception.getMessage(), "Exception message should match expected.");
    }

    @Test
    public void testSetBudgetRange_Zero() {
        ImportForm form = new ImportForm();
        BigDecimal zeroRange = BigDecimal.ZERO;
        form.setBudgetRange(zeroRange);
        assertEquals(zeroRange, form.getBudgetRange(), "Setting budget range to zero should be valid.");
    }

    @Test
    public void testSetProductName_NonEmpty() {
        ImportForm form = new ImportForm();
        String productName = "Laptop";
        form.setProductName(productName);
        assertEquals(productName, form.getProductName(), "Product name should be correctly set.");
    }

    @Test
    public void testSetProductName_Empty() {
        ImportForm form = new ImportForm();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> form.setProductName(""));
        assertEquals("Product name cannot be empty", exception.getMessage(), "Exception message should match expected.");
    }

    @Test
    public void testSetProductName_Null() {
        ImportForm form = new ImportForm();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> form.setProductName(null));
        assertEquals("Product name cannot be empty", exception.getMessage(), "Exception message should match expected.");
    }

    @Test
    public void testSetId() {
        ImportForm form = new ImportForm();
        form.setId(1);
        assertEquals(1, form.getId(), "ID should be set correctly.");
    }

    @Test
    public void testSetUserId() {
        ImportForm form = new ImportForm();
        form.setUserId(100);
        assertEquals(100, form.getUserId(), "User ID should be set correctly.");
    }

    @Test
    public void testSetExpectedCountry() {
        ImportForm form = new ImportForm();
        String country = "USA";
        form.setExpectedCountry(country);
        assertEquals(country, form.getExpectedCountry(), "Expected country should be set correctly.");
    }

    @Test
    public void testSetDetails() {
        ImportForm form = new ImportForm();
        String details = "Product details here.";
        form.setDetails(details);
        assertEquals(details, form.getDetails(), "Details should be set correctly.");
    }

    @Test
    public void testSetImage() {
        ImportForm form = new ImportForm();
        String image = "image.jpg";
        form.setImage(image);
        assertEquals(image, form.getImage(), "Image should be set correctly.");
    }

    @Test
    public void testSetStatus() {
        ImportForm form = new ImportForm();
        String status = "Pending";
        form.setStatus(status);
        assertEquals(status, form.getStatus(), "Status should be set correctly.");
    }
}

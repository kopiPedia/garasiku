package com.koped.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class TaxTest {

    @Test
    public void testIdProperty() {
        Tax tax = new Tax();
        tax.setId(1);
        assertEquals(1, tax.getId(), "The ID should be correctly set and retrieved.");
    }

    @Test
    public void testCountryProperty() {
        Tax tax = new Tax();
        tax.setCountry(100); // Assuming '100' is a valid country code or ID
        assertEquals(100, tax.getCountry(), "The country should be correctly set and retrieved.");
    }

    @Test
    public void testAreaTaxProperty() {
        Tax tax = new Tax();
        BigDecimal expectedAreaTax = new BigDecimal("15.75");
        tax.setAreaTax(expectedAreaTax);
        assertEquals(expectedAreaTax, tax.getAreaTax(), "The area tax should be correctly set and retrieved.");
    }

    @Test
    public void testAreaTaxPrecision() {
        Tax tax = new Tax();
        BigDecimal areaTaxWithHighPrecision = new BigDecimal("15.123456789");
        tax.setAreaTax(areaTaxWithHighPrecision);
        assertEquals(0, areaTaxWithHighPrecision.compareTo(tax.getAreaTax()), "The area tax should maintain its precision.");
    }
}

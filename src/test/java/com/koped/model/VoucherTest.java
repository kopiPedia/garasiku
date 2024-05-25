package com.koped.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoucherTest {
    Voucher voucher;
    @BeforeEach
    void setUp() {
        this.voucher = new Voucher("eb558e9f-1c39-460e-8860-71af6af63bd6", "GOJEKINAJA", 2, 50);
    }

    @Test
    void testGetVoucherId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.voucher.getVoucherId());
    }

    @Test
    void testGetVoucherName() {
        assertEquals("GOJEKINAJA", this.voucher.getVoucherName());
    }

    @Test
    void testGetVoucherQuantity() {
        assertEquals(2, this.voucher.getVoucherQuantity());
    }

    @Test
    void testGetDiscount() { assertEquals(50, this.voucher.getDiscount());}

    @Test
    void testSetVoucherId() {
        String newVoucherId = "a0f9de47-90b1-437d-a0bf-d0821dde9096";
        this.voucher.setVoucherId(newVoucherId);
        assertEquals(newVoucherId, this.voucher.getVoucherId());
    }

    @Test
    void testSetVoucherName() {
        String newVoucherName = "NEW_NAME";
        this.voucher.setVoucherName(newVoucherName);
        assertEquals(newVoucherName, this.voucher.getVoucherName());
    }

    @Test
    void testSetVoucherQuantity() {
        int newQuantity = 5;
        this.voucher.setVoucherQuantity(newQuantity);
        assertEquals(newQuantity, this.voucher.getVoucherQuantity());
    }

    @Test
    void testSetDiscount() {
        int newDiscount = 20;
        this.voucher.setDiscount(newDiscount);
        assertEquals(newDiscount, this.voucher.getDiscount());
    }
}
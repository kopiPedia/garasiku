package com.koped.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.koped.enums.PaymentMethod;

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
}
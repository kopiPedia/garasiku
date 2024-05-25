package com.koped.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.koped.model.Voucher;
import com.koped.repository.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VoucherServiceTest {

    @Mock
    private VoucherRepository voucherRepository;

    @InjectMocks
    private VoucherServiceImpl voucherService;

    private Voucher voucher;

    @BeforeEach
    void setUp() {
        voucher = new Voucher("1", "Test Voucher", 10, 5);
    }

    @Test
    void testCreateVoucher() {
        when(voucherRepository.save(voucher)).thenReturn(voucher);
        Voucher createdVoucher = voucherService.createVoucher(voucher);
        verify(voucherRepository, times(1)).save(voucher);
        assertEquals(voucher, createdVoucher);
    }

    @Test
    void testDeleteVoucher() {
        voucherService.deleteVoucher("1");

        verify(voucherRepository, times(1)).deleteById("1");
    }

    @Test
    void testFindByVoucherId() {
        when(voucherRepository.findById("1")).thenReturn(Optional.of(voucher));

        Voucher foundVoucher = voucherService.findByVoucherId("1").orElseThrow(null);
        assertEquals(voucher, foundVoucher);
    }

    @Test
    void testFindAllVoucher() {
        List<Voucher> vouchers = new ArrayList<>();
        vouchers.add(voucher);

        when(voucherRepository.findAll()).thenReturn(vouchers);
        List<Voucher> foundVouchers = voucherService.findAllVoucher();
        assertEquals(vouchers, foundVouchers);
    }
}
//    @Test
//    void testUpdateVoucher() {
//        Voucher updatedVoucher = new Voucher("1", "Updated Voucher", 20, 10);
//        when(voucherRepository.findById("1")).thenReturn(Optional.of(voucher));
//        when(voucherRepository.save(voucher)).thenReturn(updatedVoucher);
//
////        Voucher returnedVoucher = voucherService.updateVoucher("1", updatedVoucher);
//        verify(voucherRepository, times(1)).findById("1");
//        verify(voucherRepository, times(1)).save(voucher);
//        assertEquals(updatedVoucher, returnedVoucher);
//    }
//    @Test
//    void testUpdateVoucherWhenVoucherNotNull() {
//        Voucher voucher = new Voucher("1", "Test Voucher", 10, 5);
//        Voucher updatedVoucher = new Voucher("1", "Updated Voucher", 20, 10);
//
//        when(voucherRepository.findById("1")).thenReturn(Optional.of(voucher));
//
//        when(voucherRepository.save(any())).thenReturn(null);
//
////        Voucher returnedVoucher = voucherService.updateVoucher("1", updatedVoucher);
//        verify(voucherRepository, times(1)).findById("1");
//        verify(voucherRepository, times(1)).save(any());
//        assertNull(returnedVoucher);
//    }
//
//    @Test
//    void testUpdateVoucherWhenVoucherIsNull() {
//        when(voucherRepository.findById("1")).thenReturn(Optional.empty());
//        Voucher result = voucherService.updateVoucher("1", new Voucher("1", "Updated Voucher", 20, 10));
//
//        verify(voucherRepository, times(1)).findById("1");
//        assertNull(result);
//    }

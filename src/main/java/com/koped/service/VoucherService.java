package com.koped.service;

import java.util.List;

import com.koped.model.Voucher;

public interface VoucherService {
    Voucher createVoucher(Voucher voucher);
    void deleteVoucher(String voucherId);
    Voucher findByVoucherId(String voucherId);
    List<Voucher> findAllVoucher();
    Voucher updateVoucher(String voucherId, Voucher updatedVoucher);
}
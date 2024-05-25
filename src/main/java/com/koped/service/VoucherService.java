package com.koped.service;

import java.util.List;
import java.util.Optional;

import com.koped.model.Voucher;

public interface VoucherService {
    Voucher createVoucher(Voucher voucher);
    void deleteVoucher(String voucherId);
    public Optional<Voucher> findByVoucherId(String voucherId);
    List<Voucher> findAllVoucher();
    Voucher updateVoucher(Voucher updatedVoucher);
}
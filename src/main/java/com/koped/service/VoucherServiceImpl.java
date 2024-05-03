package com.koped.service;

import java.util.List;
import java.util.Optional;

import com.koped.model.Cart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koped.model.Voucher;
import com.koped.repository.VoucherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    @Override
    public Voucher createVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucher(String voucherId) {
        voucherRepository.deleteById(voucherId);
    }

    @Override
    public Voucher findByVoucherId(String voucherId) {
        Optional<Voucher> voucher = voucherRepository.findById(voucherId);
        return voucher.orElse(null);
    }

    @Override
    public List<Voucher> findAllVoucher() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher updateVoucher(String voucherId, Voucher updatedVoucher) {
        Voucher voucher = voucherRepository.findById(voucherId).orElse(null);

        if (voucher != null) {
            voucher.setVoucherName(updatedVoucher.getVoucherName());
            voucher.setVoucherQuantity(updatedVoucher.getVoucherQuantity());
            voucher.setDiscount(updatedVoucher.getDiscount());
            return voucherRepository.save(voucher);
        }

        return null;
    }
}

package com.koped.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.koped.model.Voucher;
import com.koped.service.VoucherServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/voucher-rest")
@RequiredArgsConstructor
public class VoucherRestController {

    private final VoucherServiceImpl voucherService;

    @GetMapping("/list")
    public List<Voucher> findAllVoucher(){
        return voucherService.findAllVoucher();
    }

    @GetMapping("/search/{voucherId}")
    public Voucher findByVoucherId(@PathVariable String voucherId) {
        return voucherService.findByVoucherId(voucherId);
    }

    @PostMapping("/create")
    public Voucher createVoucher(@ModelAttribute Voucher voucher) {
        return voucherService.createVoucher(voucher);
    }

    @DeleteMapping("/delete/{voucherId}")
    public void deleteVoucher(@PathVariable String voucherId) {
        voucherService.deleteVoucher(voucherId);
    }

    @PutMapping("/update/{voucherId}")
    public Voucher updateVoucher(@PathVariable String voucherId, @RequestBody Voucher updatedVoucher) {
        return voucherService.updateVoucher(voucherId, updatedVoucher);
    }
}
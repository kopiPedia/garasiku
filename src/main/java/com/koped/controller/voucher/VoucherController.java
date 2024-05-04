package com.koped.controller.voucher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class VoucherController {
    @GetMapping("/voucher")
    public String voucherPage() {
        return "Voucher";
    }
}
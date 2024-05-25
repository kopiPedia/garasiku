package com.koped.controller.voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.koped.model.Voucher;
import java.util.List;
import com.koped.service.VoucherService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping("/list")
    public String voucherPage(Model model) {
        List<Voucher> voucherList = voucherService.findAllVoucher();
        model.addAttribute("voucher", voucherList);
        return "Voucher/VoucherList";
    }

    @GetMapping("/create")
    public String createVoucherPage(Model model){
        Voucher voucher = new Voucher();
        model.addAttribute("voucher", voucher);
        return "Voucher/CreateVoucher";
    }

    @PostMapping("/create")
    public String createVoucherPost(@ModelAttribute Voucher voucher) {
        voucherService.createVoucher(voucher);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct (@PathVariable String id){
        voucherService.deleteVoucher(id);
        return "redirect:/voucher/list";
    }

    @GetMapping("/edit/{id}")
    public String editVoucher (@PathVariable String id, Model model) {
        Voucher voucher = voucherService.findByVoucherId(id).orElseThrow(null);
        model.addAttribute("voucher", voucher);
        model.addAttribute("voucherId", id);
        return "Voucher/EditVoucher";
    }

    @PostMapping("/update/{voucherId}")
    public String updateVoucher(@PathVariable String voucherId, @ModelAttribute("voucher") Voucher updatedVoucher) {
        Voucher voucher = voucherService.findByVoucherId(voucherId).orElseThrow(null);
        updatedVoucher.setVoucherId(voucher.getVoucherId());
        voucherService.updateVoucher(updatedVoucher);
        return "redirect:/voucher/list";
    }
}
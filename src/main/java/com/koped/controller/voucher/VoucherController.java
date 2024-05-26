package com.koped.controller.voucher;
import com.koped.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String voucherPage(Model model) {
        String userLoggedIn = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = userService.findByUsername(userLoggedIn).getRole();

        if (role.equals("Admin")){
            List<Voucher> voucherList = voucherService.findAllVoucher();
            model.addAttribute("voucher", voucherList);
            return "Voucher/VoucherList";
        }

        return "redirect:/?error=invalid_format";
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

    @PostMapping("/decrement/{voucherId}")
    @ResponseBody
    public ResponseEntity<String> useVoucher(@PathVariable String voucherId) {
        Voucher voucher = voucherService.findByVoucherId(voucherId).orElseThrow(null);
        if (voucher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voucher not found");
        }
        if (voucher.getVoucherQuantity() > 0) {
            voucher.setVoucherQuantity(voucher.getVoucherQuantity() - 1);
            if (voucher.getVoucherQuantity() == 0) {
                voucherService.deleteVoucher(voucherId);
            } else {
                voucherService.createVoucher(voucher);
            }
            return ResponseEntity.ok("Voucher used successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voucher out of stock");
        }
    }
}
package com.koped.controller.payment;

import com.koped.model.Cart;
import com.koped.model.Product;
import com.koped.model.Voucher;
import com.koped.service.CartService;
import com.koped.service.ProductService;
import com.koped.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private CartService cartService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String paymentPage(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Cart> cart = cartService.findCartByUser(username);

        double totalPrice = 0.0;
        for (Cart item : cart) {
            Product product = productService.findByProductIds(item.getProductId());
            if (product != null) {
                totalPrice += product.getPrice() * item.getQuantity();
            }
        }

        List<Voucher> voucherList = voucherService.findAllVoucher();
        model.addAttribute("vouchers", voucherList);
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);

        return "Payment/payment";
    }
}
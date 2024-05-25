package com.koped.controller.product;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koped.model.Product;
import com.koped.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductServiceImpl prodService;
	
	@GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = prodService.findAllProducts();
        model.addAttribute("products", products);
        return "main-product";
    }
	
	@GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create-product";
    }
	
	@PostMapping("/submit-product")
    public String saveFeedback(@ModelAttribute Product data, @RequestParam("images") MultipartFile images) throws IOException {
        if (images != null && !images.isEmpty()) {
        	prodService.createNewProduct(data, images);
        }
        return "redirect:/home";
    }

}
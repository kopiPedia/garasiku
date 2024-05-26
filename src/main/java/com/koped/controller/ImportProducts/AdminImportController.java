package com.koped.controller.ImportProducts;

import com.koped.model.User;
import com.koped.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.koped.model.ImportForm;
import com.koped.model.ImportProduct;
import com.koped.service.ImportProductFormService;
import com.koped.service.ImportProductService;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/admin/import")
public class AdminImportController {

    @Autowired
    private ImportProductFormService importProductFormService;

    @Autowired
    private ImportProductService importProductService;

    @Autowired
    private UserServiceImpl userService;

    private String getCurrentRole() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User dataUser = userService.findByUsername(username);
        return dataUser.getRole();
    }

    @GetMapping("/main")
    public String importMainPage(Model model) {

        String role = getCurrentRole();

        if ("Admin".equals(role)) {

            List<ImportForm> importForms = importProductFormService.findAllRequests();
        List<ImportProduct> importProducts = importProductService.findAllProducts();
        model.addAttribute("importForms", importForms);
        model.addAttribute("importProducts", importProducts);
        return "AdminImport/import-main";
        } else {
            return "redirect:/admin/import/access-denied-import";
    }
    }

    @GetMapping("/access-denied-import")
    public String accessDeniedImport() {
        return "access-denied-import";
    }

    @GetMapping("/form/view/{requestId}")
    public String viewFormPage(@PathVariable String requestId, Model model) {
        ImportForm importForm = importProductFormService.findByRequestIds(requestId).getBody();
        model.addAttribute("importForm", importForm);
        return "AdminImport/view-import-form";
    }

    @PostMapping("/form/updateStatus")
    public String updateFormStatus(@RequestParam String requestId, @RequestParam String status) throws IOException {
        ImportForm importForm = importProductFormService.findByRequestIds(requestId).getBody();
        if (importForm != null) {
            importForm.setStatus(status);
            importProductFormService.updateByRequestIds(requestId, importForm, null);
        }
        return "redirect:/admin/import/form/view/" + requestId;
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model) {
        model.addAttribute("importProduct", new ImportProduct());
        List<ImportForm> importForms = importProductFormService.findAllRequests();
        model.addAttribute("importForms", importForms);
        return "AdminImport/create-import-product";
    }

    @PostMapping("/product/create")
    public String createProduct(@ModelAttribute ImportProduct importProduct, @RequestParam("productImages") MultipartFile productImage) throws IOException {
        importProductService.createNewProduct(importProduct, productImage);
        return "redirect:/admin/import/main";
    }

    @GetMapping("/product/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        ImportProduct importProduct = importProductService.findByProductIds(productId).getBody();
        model.addAttribute("importProduct", importProduct);
        List<ImportForm> importForms = importProductFormService.findAllRequests();
        model.addAttribute("importForms", importForms);
        return "AdminImport/edit-import-product";
    }

    @PostMapping("/product/edit")
    public String updateProduct(@ModelAttribute ImportProduct importProduct, @RequestParam("productImages") MultipartFile productImage) throws IOException {
        importProductService.updateByProductId(importProduct.getProductId(), importProduct, productImage);
        return "redirect:/admin/import/main";
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        importProductService.deleteByProductId(productId);
        return "redirect:/admin/import/main";
    }

    @GetMapping("/product/view/{productId}")
    public String viewProductPage(@PathVariable String productId, Model model) {
        ImportProduct importProduct = importProductService.findByProductIds(productId).getBody();
        model.addAttribute("importProduct", importProduct);
        return "AdminImport/view-import-product";
    }
}

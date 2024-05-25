package com.koped.controller.ImportProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.koped.model.ImportForm;
import com.koped.model.ImportProduct;
import com.koped.service.ImportProductFormService;
import com.koped.service.ImportProductService;

import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportFormController {

    @Autowired
    private ImportProductFormService importProductFormService;

    @Autowired
    private ImportProductService importProductService;

    // Hardcoded user ID for now
    private final int userId = 2;

    @GetMapping("/forms")
    public String importFormsPage(Model model) {
        List<ImportForm> importForms = importProductFormService.findAllByUserId(userId);
        List<ImportProduct> importProducts = importProductService.findAllProducts();
        model.addAttribute("importForms", importForms);
        model.addAttribute("importProducts", importProducts);
        return "Import/import-form";
    }

    @GetMapping("/form/create")
    public String createFormPage(Model model) {
        model.addAttribute("importForm", new ImportForm());
        return "Import/create-import-form";
    }

    @PostMapping("/form/create")
    public String createForm(@ModelAttribute ImportForm importForm) {
        importForm.setUserId(userId);
        importProductFormService.createNewRequests(importForm);
        return "redirect:/import/forms";
    }

    @GetMapping("/form/edit/{requestId}")
    public String editFormPage(@PathVariable String requestId, Model model) {
        ImportForm importForm = importProductFormService.findByRequestIds(requestId).getBody();
        model.addAttribute("importForm", importForm);
        return "Import/edit-import-form";
    }

    @PostMapping("/form/edit")
    public String updateForm(@ModelAttribute ImportForm importForm) {
        importProductFormService.updateByRequestIds(importForm.getRequestId(), importForm);
        return "redirect:/import/forms";
    }

    @GetMapping("/form/delete/{requestId}")
    public String deleteForm(@PathVariable String requestId) {
        importProductFormService.deleteByRequestId(requestId);
        return "redirect:/import/forms";
    }

    @GetMapping("/form/view/{requestId}")
    public String viewFormPage(@PathVariable String requestId, Model model) {
        ImportForm importForm = importProductFormService.findByRequestIds(requestId).getBody();
        model.addAttribute("importForm", importForm);
        return "Import/view-import-form";
    }

    @GetMapping("/products")
    public String importProductsPage(Model model) {
        List<ImportProduct> importProducts = importProductService.findAllProducts();
        model.addAttribute("importProducts", importProducts);
        return "Import/import-products";
    }

    @GetMapping("/product/view/{productId}")
    public String viewProductPage(@PathVariable String productId, Model model) {
        ImportProduct importProduct = importProductService.findByProductIds(productId).getBody();
        model.addAttribute("importProduct", importProduct);
        return "view-import-product";
    }
}

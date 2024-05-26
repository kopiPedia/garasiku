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
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/import")
public class ImportFormController {

    @Autowired
    private ImportProductFormService importProductFormService;

    @Autowired
    private ImportProductService importProductService;

    @Autowired
    private UserServiceImpl userService;

    private int getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User dataUser = userService.findByUsername(username);
        return dataUser.getId();
    }
    @GetMapping("/forms")
    public String importFormsPage(Model model) {
        int userId = getCurrentUserId();
        List<ImportForm> importForms = importProductFormService.findAllByUserId(userId);
        List<ImportProduct> importProducts = importProductService.findAllProducts();

        // Group products by requestId
        Map<String, List<ImportProduct>> productsByRequestId = importProducts.stream()
                .collect(Collectors.groupingBy(ImportProduct::getRequestId));

        model.addAttribute("importForms", importForms);
        model.addAttribute("productsByRequestId", productsByRequestId);
        return "Import/import-form";
    }

    @GetMapping("/form/create")
    public String createFormPage(Model model) {
        model.addAttribute("importForm", new ImportForm());
        return "Import/create-import-form";
    }

    @PostMapping("/form/create")
    public String createForm(@ModelAttribute ImportForm importForm, @RequestParam("images") MultipartFile image) throws IOException {
        int userId = getCurrentUserId();
        importForm.setUserId(userId);
        importForm.setStatus("PLACED");  // Automatically set status to PLACED
        importProductFormService.createNewRequests(importForm, image);
        return "redirect:/import/forms";
    }

    @GetMapping("/form/edit/{requestId}")
    public String editFormPage(@PathVariable String requestId, Model model) {
        ImportForm importForm = importProductFormService.findByRequestIds(requestId).getBody();
        model.addAttribute("importForm", importForm);
        return "Import/edit-import-form";
    }

    @PostMapping("/form/edit")
    public String updateForm(@ModelAttribute ImportForm importForm, @RequestParam("images") MultipartFile image) throws IOException {
        importProductFormService.updateByRequestIds(importForm.getRequestId(), importForm, image);
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

    @GetMapping("/product/view/{productId}")
    public String viewProductPage(@PathVariable String productId, Model model) {
        ImportProduct importProduct = importProductService.findByProductIds(productId).getBody();
        model.addAttribute("importProduct", importProduct);
        return "Import/view-import-product";
    }
}

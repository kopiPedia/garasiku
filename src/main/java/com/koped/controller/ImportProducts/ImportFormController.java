package com.koped.controller.ImportProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koped.model.ImportForm;
import com.koped.model.ImportProduct;
import com.koped.service.ImportProductFormService;
import com.koped.service.ImportProductService;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ImportFormController {

    @Autowired
    private ImportProductFormService importProductFormService;

    @Autowired
    private ImportProductService importProductService;

    @GetMapping("/importform")
    public String importFormsPage(Model model) {
        // Assume userId is retrieved from session or authentication
        int userId = 2; // Dummy userId for now

        List<ImportForm> importForms = importProductFormService.findAllByUserId(userId);
        List<ImportProduct> importProducts = importProductService.findAllProducts(); // Assuming you fetch all products

        model.addAttribute("importForms", importForms);
        model.addAttribute("importProducts", importProducts);
        return "import-form";
    }

}

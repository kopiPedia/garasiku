package com.koped.controller.importform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koped.model.ImportForm;
import com.koped.service.ImportProductFormService;

import java.util.List;

@Controller
public class ImportFormController {

    @Autowired
    private ImportProductFormService importProductFormService;

    @GetMapping("/importform")
    public String importFormsPage(Model model) {
        // Assume userId is retrieved from session or authentication
        int userId = 2; // Dummy userId for now, replace with actual userId retrieval logic

        List<ImportForm> importForms = importProductFormService.findAllByUserId(userId);
        model.addAttribute("importForms", importForms);
        return "ImportForm"; // Assuming you have an import-forms.html template
    }
}

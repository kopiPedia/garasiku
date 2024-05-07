package com.koped.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koped.model.ImportForm;
import com.koped.service.ImportProductFormServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/importform")
@RequiredArgsConstructor
public class ImportFormRestController {

    private final ImportProductFormServiceImpl importFormService;

    @GetMapping("/list")
    public List<ImportForm> findAllRequests() {
        return importFormService.findAllRequests();
    }

    @GetMapping("/search/{requestId}")
    public ImportForm findByRequestId(@PathVariable String requestId) {
        return importFormService.findByRequestIds(requestId);
    }

    @PostMapping("/create")
    public ImportForm createNewRequests(@RequestBody ImportForm data) {
        return importFormService.createNewRequests(data);
    }

    @DeleteMapping("/delete/{requestId}")
    public String deleteRequestByRequestId(@PathVariable String requestId) {
        return importFormService.deleteByRequestId(requestId);
    }

    @PutMapping("/update")
    public ImportForm updateRequestByRequestId(@RequestBody ImportForm data) {
        return importFormService.updateByRequestIds(data);
    }
}

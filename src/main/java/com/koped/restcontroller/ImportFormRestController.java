package com.koped.restcontroller;

import com.koped.model.ImportForm;
import com.koped.service.ImportProductFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/importform")
@RequiredArgsConstructor
public class ImportFormRestController {

    private final ImportProductFormService importProductFormService;

    @GetMapping("/list")
    public List<ImportForm> findAllRequests() {
        return importProductFormService.findAllRequests();
    }

    @GetMapping("/search/{requestId}")
    public ResponseEntity<ImportForm> findByRequestIds(@PathVariable String requestId) {
        return importProductFormService.findByRequestIds(requestId);
    }
//
//    @PostMapping("/create")
//    public ResponseEntity<ImportForm> createNewRequests(@RequestBody ImportForm data) {
//        return importProductFormService.createNewRequests(data, images);
//    }

    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<String> deleteRequestByRequestId(@PathVariable String requestId) {
        return importProductFormService.deleteByRequestId(requestId);
    }

//    @PutMapping("/update/{requestId}")
//    public ResponseEntity<ImportForm> updateRequestByRequestId(@PathVariable String requestId, @RequestBody ImportForm data) {
//        return importProductFormService.updateByRequestIds(requestId, data, image);
//    }

    @GetMapping("/user/{userId}")
    public List<ImportForm> findAllByUserId(@PathVariable int userId) {
        return importProductFormService.findAllByUserId(userId);
    }
}

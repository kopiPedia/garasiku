package com.koped.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koped.model.ImportProduct;
import com.koped.service.ImportProductService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/importproduct")
@RequiredArgsConstructor
public class ImportProductRestController {

    private final ImportProductService importProductService;

    @GetMapping("/list")
    public List<ImportProduct> findAllProducts() {
        return importProductService.findAllProducts();
    }

    @GetMapping("/search/{productId}")
    public ResponseEntity<ImportProduct> findByProductIds(@PathVariable String productId) {
        return importProductService.findByProductIds(productId);
    }

    @PostMapping("/create")
    public ResponseEntity<ImportProduct> createNewProduct(@RequestBody ImportProduct data) {
        return importProductService.createNewProduct(data);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProductByProductId(@PathVariable String productId) {
        return importProductService.deleteByProductId(productId);
    }

    @PutMapping("/update/{productId}") // Add productId path variable here
    public ResponseEntity<ImportProduct> updateProductByProductId(@PathVariable String productId, @RequestBody ImportProduct data) {
        return importProductService.updateByProductId(productId, data);
    }


    @GetMapping("/search/user/{userId}")
    public ResponseEntity<ImportProduct> findByUserIds(@PathVariable int userId) {
        return importProductService.findByUserId(userId);
    }
}

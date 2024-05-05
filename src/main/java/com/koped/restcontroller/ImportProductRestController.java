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

import com.koped.model.ImportProduct;
import com.koped.service.ImportProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/importproduct")
@RequiredArgsConstructor
public class ImportProductRestController {

    private final ImportProductServiceImpl ImportprodService;


    @GetMapping("/list")
    public List<ImportProduct> findAllProducts(){
        return ImportprodService.findAllProducts();
    }

    @GetMapping("/search/{productid}")
    public ImportProduct findByProductIds(@PathVariable String productid) {
        return ImportprodService.findByProductIds(productid);
    }

    @PostMapping("/create")
    public ImportProduct createNewProduct(@RequestBody ImportProduct data) {
        return ImportprodService.createNewProduct(data);
    }

    @DeleteMapping("/delete/{productid}")
    public String deleteProductByProductId(@PathVariable String productid) {
        return ImportprodService.deleteByProductId(productid);
    }

    @PutMapping("/update")
    public ImportProduct updateProductByProductId(@RequestBody ImportProduct data) {
        return ImportprodService.updateByProductIds(data);
    }

    @GetMapping("/search/{userid}")
    public ImportProduct findByUserIds(@PathVariable int userid) {
        return ImportprodService.findByUserId(userid);
    }

}

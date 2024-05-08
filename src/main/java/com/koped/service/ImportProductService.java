package com.koped.service;

import com.koped.model.ImportProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImportProductService {

    ResponseEntity<ImportProduct> findByProductIds(String productId);
    List<ImportProduct> findAllProducts();
    ResponseEntity<String> deleteByProductId(String productId);
    ResponseEntity<ImportProduct> updateByProductId(String productId, ImportProduct data);
    ResponseEntity<ImportProduct> createNewProduct(ImportProduct data);
    ResponseEntity<ImportProduct> findByUserId(int userId);
}

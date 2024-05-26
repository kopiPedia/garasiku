package com.koped.service;

import com.koped.model.ImportProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public interface ImportProductService {
    ResponseEntity<ImportProduct> findByProductIds(String productId);
    List<ImportProduct> findAllProducts();
    ResponseEntity<String> deleteByProductId(String productId);
    ResponseEntity<ImportProduct> updateByProductId(String productId, ImportProduct updatedProductData, MultipartFile productImage) throws IOException;
    ResponseEntity<ImportProduct> createNewProduct(ImportProduct data, MultipartFile productImage) throws IOException;
    ResponseEntity<ImportProduct> findByUserId(int userId);
}
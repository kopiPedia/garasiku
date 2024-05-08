package com.koped.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.koped.model.ImportProduct;

import com.koped.repository.ImportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;


@Service
@RequiredArgsConstructor
@Transactional
public class ImportProductServiceImpl implements ImportProductService {

    private final ImportRepository importRepo;

    @Override
    public ResponseEntity<ImportProduct> findByProductIds(String productId) {
        ImportProduct product = importRepo.findByProductId(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Override
    public List<ImportProduct> findAllProducts() {
        return importRepo.findAll();
    }

    @Override
    public ResponseEntity<String> deleteByProductId(String productId) {
        ImportProduct product = importRepo.findByProductId(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        importRepo.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

    @Override
    public ResponseEntity<ImportProduct> updateByProductId(String productId, ImportProduct updatedProductData) {
        // Find the existing product by productId
        ImportProduct existingProduct = importRepo.findByProductId(productId);
        if (existingProduct == null) {
            // If the product with the given productId doesn't exist, return a NOT_FOUND response
            return ResponseEntity.notFound().build();
        }

        // Update the fields of the existing product with the new data
        existingProduct.setTitle(updatedProductData.getTitle());
        existingProduct.setDescription(updatedProductData.getDescription());
        existingProduct.setStock(updatedProductData.getStock());
        existingProduct.setCountry(updatedProductData.getCountry());
        existingProduct.setPrice(updatedProductData.getPrice());
        existingProduct.setCategory(updatedProductData.getCategory());
        existingProduct.setImage(updatedProductData.getImage());
        existingProduct.setUserId(updatedProductData.getUserId());

        // Save the updated product
        ImportProduct updatedProduct = importRepo.save(existingProduct);

        // Return the updated product in the response
        return ResponseEntity.ok(updatedProduct);
    }

    @Override
    public ResponseEntity<ImportProduct> createNewProduct(ImportProduct data) {
        // Validate the fields before saving
        ResponseEntity<String> validationResponse = validateImportProduct(data);
        if (validationResponse != null) {
            // Return the validation response if there are errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Generate unique ID if not provided
        if (data.getProductId() == null || data.getProductId().isEmpty()) {
            data.setProductId(generateUniqueProductId());
        }

        ImportProduct savedProduct = importRepo.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @Override
    public ResponseEntity<ImportProduct> findByUserId(int userId) {
        ImportProduct product = importRepo.findByUserId(userId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    // Method to validate the ImportProduct fields
    private ResponseEntity<String> validateImportProduct(ImportProduct product) {
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product data cannot be null");
        }
        if (product.getTitle() == null || product.getTitle().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title cannot be null or empty");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Description cannot be null or empty");
        }
        if (product.getStock() == null || product.getStock() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock must be greater than 0");
        }
        if (product.getCountry() == null || product.getCountry().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price must be greater than 0");
        }
        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category cannot be null or empty");
        }
        // If all validations pass, return null to indicate success
        return null;
    }

    // Method to generate unique product ID
    private String generateUniqueProductId() {
        // Generate UUID as product ID
        return UUID.randomUUID().toString();
    }
}


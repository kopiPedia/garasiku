package com.koped.service;


import java.util.List;
import java.util.UUID;

import com.koped.model.ImportProduct;
import com.koped.repository.ImportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import com.koped.repository.CartRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class ImportProductServiceImpl implements ImportProductService {

    private final ImportRepository importRepo;
    private final CloudinaryServiceImpl cloudService;
    private final CartRepository cartRepo;

    @Override
    public ResponseEntity<ImportProduct> findByProductIds(String productId) {
        ImportProduct product = importRepo.findByProductId(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Override
    public ImportProduct findByProductId(String productId) {
        return importRepo.findByProductId(productId);
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
        cartRepo.deleteByProductId(productId);
        importRepo.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

    @Override
    public ResponseEntity<ImportProduct> updateByProductId(String productId, ImportProduct updatedProductData, MultipartFile productImage) throws IOException {
        ImportProduct existingProduct = importRepo.findByProductId(productId);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        if (productImage != null && !productImage.isEmpty()) {
            try {

                existingProduct.setImage(cloudService.uploadFile(productImage, "folder_1"));

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        existingProduct.setTitle(updatedProductData.getTitle());
        existingProduct.setDescription(updatedProductData.getDescription());
        existingProduct.setStock(updatedProductData.getStock());
        existingProduct.setCountry(updatedProductData.getCountry());
        existingProduct.setPrice(updatedProductData.getPrice());
        existingProduct.setCategory(updatedProductData.getCategory());
        existingProduct.setUserId(updatedProductData.getUserId());

        ImportProduct updatedProduct = importRepo.save(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @Override
    public ResponseEntity<ImportProduct> createNewProduct(ImportProduct data, MultipartFile productImage) throws IOException {
        if (productImage != null && !productImage.isEmpty()) {
            try {

                data.setImage(cloudService.uploadFile(productImage, "folder_1"));

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        data.setProductId(UUID.randomUUID().toString());
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
        if (product.getPrice() == null || product.getPrice() <= 0.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price must be greater than 0");
        }
        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category cannot be null or empty");
        }
        return null;
    }

    private String generateUniqueProductId() {
        return UUID.randomUUID().toString();
    }
}

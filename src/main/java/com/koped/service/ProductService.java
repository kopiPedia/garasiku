package com.koped.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.koped.model.Product;

public interface ProductService {
	
	Product findByProductIds(String productId);
	List<Product> findAllProducts();
	Boolean deleteByProductId(String productId);
	Product updateByProductIds(Product data, MultipartFile newImage);
	ResponseEntity<?> createNewProduct(Product data, MultipartFile image) throws IOException;
	Product findByIds(Integer id);

}

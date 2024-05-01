package com.koped.service;

import java.util.List;

import com.koped.model.Product;

public interface ProductService {
	
	Product findByProductIds(String productId);
	List<Product> findAllProducts();
	String deleteByProductId(String productId);
	Product updateByProductIds(Product data);
	Product createNewProduct(Product data);

}

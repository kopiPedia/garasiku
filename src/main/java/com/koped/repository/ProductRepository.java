package com.koped.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koped.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product findByProductId(String productId);
	String deleteByProductId(String productId);

}

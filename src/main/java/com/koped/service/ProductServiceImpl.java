package com.koped.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koped.model.Product;
import com.koped.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository prodRepo;

	@Override
	public Product findByProductIds(String productId) {
		return prodRepo.findByProductId(productId);
	}

	@Override
	public List<Product> findAllProducts() {
		return prodRepo.findAll();
	}

	@Override
	public String deleteByProductId(String productId) {
		return prodRepo.deleteByProductId(productId);
	}

	@Override
	public Product updateByProductIds(Product data) {
		return prodRepo.save(data);
	}

	@Override
	public Product createNewProduct(Product data) {
		return prodRepo.save(data);
	}

}

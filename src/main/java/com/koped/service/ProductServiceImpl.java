package com.koped.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.koped.model.Product;
import com.koped.repository.CartRepository;
import com.koped.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository prodRepo;
	private final CloudinaryServiceImpl cloudService;
	private final CartRepository cartRepo;

	@Override
	public Product findByProductIds(String productId) {
		return prodRepo.findByProductId(productId);
	}

	@Override
	public List<Product> findAllProducts() {
		return prodRepo.findAll();
	}

	@Override
	public Boolean deleteByProductId(String productId) {
		try {
			cartRepo.deleteByProductId(productId);
			prodRepo.deleteByProductId(productId);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Product updateByProductIds(Product data, MultipartFile newImage) {
		Product oldProduct = prodRepo.findById(data.getId()).orElse(null);
		
		if (newImage != null && !newImage.isEmpty()) {

			try {

				data.setImage(cloudService.uploadFile(newImage, "folder_1"));

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		} else {
			data.setImage(oldProduct.getImage());
		}
		
		data.setProductId(oldProduct.getProductId());
		prodRepo.save(data);
		return data;
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<?> createNewProduct(Product data, MultipartFile image) throws IOException {
		if (image != null || !image.isEmpty()) {

			String randomUUID = String.valueOf(UUID.randomUUID().toString());

			try {

				data.setImage(cloudService.uploadFile(image, "folder_1"));

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			data.setProductId(randomUUID);
			prodRepo.save(data);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public Product findByIds(Integer id) {
		return prodRepo.findById(id).orElse(null);
	}

}

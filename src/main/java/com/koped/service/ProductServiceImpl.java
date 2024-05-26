package com.koped.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
	public Boolean deleteByProductId(String productId) {
		try {
			prodRepo.deleteByProductId(productId);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public Product updateByProductIds(Product data) {
		return prodRepo.save(data);
	}

	@SuppressWarnings("null")
	@Override
	public Product createNewProduct(Product data, MultipartFile image) throws IOException {
		if(image != null || !image.isEmpty()) {
			
			String randomUUID = String.valueOf(UUID.randomUUID().toString());
			
			String fileName = String.valueOf(randomUUID + "_" + StringUtils.cleanPath(image.getOriginalFilename()));
            String uploadDir = "src/main/resources/static/productImages/";
            String uploadPath = uploadDir + fileName;
            Path uploadAbsolutePath = Paths.get(uploadPath);
            Files.createDirectories(uploadAbsolutePath.getParent());
            Files.copy(image.getInputStream(), uploadAbsolutePath);
            
            String fileNameDB = "../productImages/" + fileName;
            
            data.setImage(fileNameDB);
            data.setProductId(randomUUID);
		}
		return prodRepo.save(data);
	}

	@Override
	public Product findByIds(Integer id) {
		return prodRepo.findById(id).orElse(null);
	}

}

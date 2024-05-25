package com.koped.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koped.model.Product;
import com.koped.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {
	
	private final ProductServiceImpl prodService;
	
	
	@GetMapping("/list")
	public List<Product> findAllProducts(){
		return prodService.findAllProducts();
	}
	
	@GetMapping("/search/{productid}")
	public Product findByProductIds(@PathVariable String productid) {
		return prodService.findByProductIds(productid);
	}
	
//	@PostMapping("/create")
//	public Product createNewProduct(@RequestBody Product data) {
//		return prodService.createNewProduct(data);
//	}
	
	@DeleteMapping("/delete/{productid}")
	public String deleteProductByProductId(@PathVariable String productid) {
		return prodService.deleteByProductId(productid);
	}
	
	@PutMapping("/update")
	public Product updateProductByProductId(@RequestBody Product data) {
		return prodService.updateByProductIds(data);
	}

}

package com.koped.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        try {
            boolean isDeleted = prodService.deleteByProductId(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

}

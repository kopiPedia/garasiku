package com.koped.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tbl_product")
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Double price;

	@Column(name = "category")
	private String category;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "image")
	private String image;

	@Column(name = "productId", unique = true)
	private String productId;

	
	public Product() {
		
	}

	public Product(String title, String description, Double price, String category, Integer stock,
			String image, String productId) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.stock = stock;
		this.image = image;
		this.productId = productId;
	}

	public void setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("Id must be > 0");

		} else {
			this.id = id;
		}
	}

	public void setPrice(Double price) {
		if (price != null && price >= 0) {
			this.price = price;
		} else {
			throw new IllegalArgumentException("Price must be >= 0");
		}
	}

	public void setStock(Integer stock) {
		if (stock != null && stock >= 0) {
			this.stock = stock;
		} else {
			throw new IllegalArgumentException("Stock cannot be null or negative");
		}
	}
	
}

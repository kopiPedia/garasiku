package com.koped.model;

import java.math.BigDecimal;

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
	private BigDecimal price;

	@Column(name = "category")
	private String category;

	@Column(name = "country")
	private String country;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "image")
	private String image;

	@Column(name = "productId", unique = true)
	private String productId;

	
	public Product() {
		
	}

	public Product(String title, String description, BigDecimal price, String category, String country, Integer stock,
			String image, String productId) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.country = country;
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

	public void setPrice(BigDecimal price) {
		if (price != null && price.compareTo(BigDecimal.ZERO) >= 0) {
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

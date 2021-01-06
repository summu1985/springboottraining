package com.sanaari.springboot.training.jsonjpademo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer productId;
	private String name;
	private String description;
	private double rating;
	private Integer quantity;
	
	public Product() {
	}

	public Product(Integer productId, String name, String description, double rating, int quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.quantity = quantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", name=" + name + ", description=" + description + ", rating="
				+ rating + ", quantity=" + quantity + "]";
	}
	
	
}

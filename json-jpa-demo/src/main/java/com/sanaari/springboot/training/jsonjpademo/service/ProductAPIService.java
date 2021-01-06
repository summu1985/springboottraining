package com.sanaari.springboot.training.jsonjpademo.service;

import java.util.List;
import java.util.Optional;

import com.sanaari.springboot.training.jsonjpademo.domain.Product;

public interface ProductAPIService {
	public List<Product> getAllProducts();
	public Optional<Product> getProductById(int id);
	public Product addProduct(Product product);
	public int deleteProduct(int id);
	public int updateProduct(int id, Product product);
}

package com.sanaari.springboot.training.crmwebapp.service;

import java.util.List;
import java.util.Optional;

import com.sanaari.springboot.training.crmwebapp.domain.Product;

public interface ProductAPIService {
	public List<Product> getAllProducts();
	public Optional<Product> getProductById(int id);
	public Product addProduct(Product product);
	public void deleteProduct(int id);
	public Optional<Product> updateProduct(int id, Product product);
}

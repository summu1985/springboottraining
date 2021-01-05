package com.sanaari.springboot.training.crmwebapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.model.ProductModel;

public interface ProductAPIService {
	public List<Product> getAllProducts();
	public Optional<Product> getProductById(int id);
	public Product addProduct(Product product);
	public int deleteProduct(int id);
	public int updateProduct(int id, Product product);
	public Page<Product> getAllProductsPaged(Pageable pageable);// Uses PagedRepository
}

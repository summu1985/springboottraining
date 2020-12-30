package com.sanaari.springboot.training.crmwebapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.repositories.ProductRepository;

@Service
public class ProductAPIServiceImpl implements ProductAPIService {

	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return (List<Product>) prodRepo.findAll();
	}

	@Override
	public Optional<Product> getProductById(int id) {
		// TODO Auto-generated method stub
		return prodRepo.findById(id);
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return prodRepo.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		prodRepo.deleteById(id);
	}

	@Override
	public Optional<Product> updateProduct(int id, Product product) {
		// TODO Auto-generated method stub
		Optional<Product> productToUpdate = prodRepo.findById(id);
		productToUpdate.get().setDescription(product.getDescription());
		productToUpdate.get().setName(product.getName());
		productToUpdate.get().setQuantity(product.getQuantity());
		productToUpdate.get().setRating(product.getRating());
		prodRepo.save(productToUpdate.get());
		return productToUpdate;
	}

}

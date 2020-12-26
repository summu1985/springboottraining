package com.sanaari.springboot.training.crmwebapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.repositories.ProductRepository;

@RestController
@RequestMapping("/store/api")
public class ProductAPIController {
	@Autowired
	private ProductRepository prodRepo;
	
	// Get All Products
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return (List<Product>) prodRepo.findAll();
	}

	// Get the product details by
	// ID
	/* @CrossOrigin(origins = "http://localhost:9090") */
	@GetMapping("/products/{id}")
	public Optional<Product> getProductById(@PathVariable(value = "id") int id) {
		return prodRepo.findById(id);
	}
}

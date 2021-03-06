package com.sanaari.springboot.training.crmwebapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.repositories.ProductRepository;

@Controller
@RequestMapping("/store")
public class ProductController {

	@Autowired
	private ProductRepository prodRepo;

	@GetMapping("/products")
	public String products(Model model) {
		List<Product> products = (List<Product>) prodRepo.findAll();
		model.addAttribute("products", products);
		return "products";
	}

	@GetMapping("/add-product")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "addproduct";
	}

	@RequestMapping(value="/product", method = RequestMethod.POST)
	public String doAddProduct(Product product, Model model) {
		Product addedProduct = prodRepo.save(product);
		String response;
		if (addedProduct == null) {
			response = "Product - " + product.getName()+" could not be added !!";
		} else {
			response = "Product - " + product.getName()+" was successfully added with product id - " + product.getProductId();
		}
		model.addAttribute("response", response);
		return "productadded";
	}
}

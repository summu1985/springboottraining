package com.sanaari.springboot.training.crmwebapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.repositories.ProductRepository;

@Controller
@RequestMapping("/store/products")
public class ProductController {

	@Autowired
	private ProductRepository prodRepo;

	@GetMapping("/list")
	public String products(Model model) {
		List<Product> products = (List<Product>) prodRepo.findAll();
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public Optional<Product> product(@PathVariable(value = "id") int id) {
		return prodRepo.findById(id);
	}

	@GetMapping("/add")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "addproduct";
	}

	@RequestMapping(value="/postadd", method = RequestMethod.POST)
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

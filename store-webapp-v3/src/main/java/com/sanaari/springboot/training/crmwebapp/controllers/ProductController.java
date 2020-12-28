package com.sanaari.springboot.training.crmwebapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.repositories.ProductRepository;

@Controller
@RequestMapping("/store/v3")
public class ProductController {

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/products/list")
	public String products(Model model) {
		ResponseEntity<Product[]> response = restTemplate.getForEntity("http://product-service/store/api/v2/products/",
				Product[].class);
		List<Product> products = new ArrayList<Product>();
		products = Arrays.asList(response.getBody());
		products.sort((p1,p2)->p1.getProductId() - p2.getProductId());
		model.addAttribute("products", products);
		return "products";
	}

	@GetMapping("/product/show")
	public String showProduct() {
		return "showproduct";
	}

	@GetMapping("/product/delete")
	public String deleteProduct() {
		return "deleteproduct";
	}
	
	@RequestMapping(value = "/product/dodelete", method = RequestMethod.GET)
	public String doDeleteProduct(@RequestParam(value = "id", required = true) String id, Model model) {

		String deleteURL = "http://product-service/store/api/v2/product/" + id + "/";
		String response;
		try {
			restTemplate.delete(deleteURL);
			response = "Product id - "+id+" successfully deleted.";
		} catch (Exception ex) {
			response = "Product id - "+id+" could not be deleted !";
		}

		model.addAttribute("response",response);
		return "productadded";
	}

	@RequestMapping(value = "/product/doshow", method = RequestMethod.GET)
	public String doShowproduct(@RequestParam(value = "id", required = true) String id, Model model) {

		ResponseEntity<Product> response = restTemplate
				.getForEntity("http://product-service/store/api/v2/products/" + id + "/", Product.class);
		Product product = response.getBody();
		model.addAttribute("product", product);
		return "product";
	}

	@GetMapping("/product/add")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "addproduct";
	}

	@RequestMapping(value = "/product/postadd", method = RequestMethod.POST)
	public String doAddProduct(Product product, Model model) {
		Product addedProduct = prodRepo.save(product);
		String response;
		if (addedProduct == null) {
			response = "Product - " + product.getName() + " could not be added !!";
		} else {
			response = "Product - " + product.getName() + " was successfully added with product id - "
					+ product.getProductId();
		}
		model.addAttribute("response", response);
		return "productadded";
	}
}

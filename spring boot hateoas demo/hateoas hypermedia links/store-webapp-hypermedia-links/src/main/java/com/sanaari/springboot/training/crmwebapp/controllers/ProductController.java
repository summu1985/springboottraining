package com.sanaari.springboot.training.crmwebapp.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.domain.ProductApiResponse;
import com.sanaari.springboot.training.crmwebapp.model.ProductModel;
import com.sanaari.springboot.training.crmwebapp.repositories.ProductRepository;

@Controller
@RequestMapping("/v4")
public class ProductController {

	@Autowired
	ProductRepository prodRepo;

	@Value("${productSvcBaseURL}")
	String productServiceBaseURL;

	/* final String productServiceBaseURL = "http://localhost:8080/v4"; */

	/*
	 * @Autowired RestTemplate restTemplate;
	 */
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/products/list")
	public String products(Model model) {
		List<Product> products = new ArrayList<Product>();

		ResponseEntity<CollectionModel<ProductModel>> response = restTemplate.exchange(
				productServiceBaseURL + "/products/", HttpMethod.GET, null,
				new ParameterizedTypeReference<CollectionModel<ProductModel>>() {
				});
		if (response.getStatusCode() == HttpStatus.OK) {
			CollectionModel<ProductModel> productModelCollection = response.getBody();
			Iterator<ProductModel> iter = productModelCollection.getContent().iterator();
			while (iter.hasNext()) {
				ProductModel productmodel = iter.next();
				/*
				 * Product product = new Product();
				 * product.setProductId(productmodel.getProductId());
				 * product.setDescription(productmodel.getDescription());
				 * product.setName(productmodel.getName());
				 * product.setQuantity(productmodel.getQuantity());
				 * product.setRating(productmodel.getRating());
				 */
				Product product = productmodel.toEntity();

				products.add(product);
			}
		}
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

		String deleteURL = productServiceBaseURL + "/product/" + id + "/";
		String response;
		try {
			restTemplate.delete(deleteURL);
			response = "Product id - " + id + " successfully deleted.";
		} catch (Exception ex) {
			response = "Product id - " + id + " could not be deleted !";
		}

		model.addAttribute("response", response);
		return "productadded";
	}

	@RequestMapping(value = "/product/doshow", method = RequestMethod.GET)
	public String doShowproduct(@RequestParam(value = "id", required = true) String id, Model model) {
		try {
			ResponseEntity<ProductModel> response = restTemplate
					.getForEntity(productServiceBaseURL + "/products/" + id + "/", ProductModel.class);
			ProductModel productModel = response.getBody();
			model.addAttribute("product", productModel.toEntity());
		} catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());

			model.addAttribute("product", null);

		}

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

		String addProductURL = productServiceBaseURL + "/product/";
		String response = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Product> request = new HttpEntity<Product>(product, headers);

		ResponseEntity<ProductApiResponse> apiResponse = restTemplate.postForEntity(addProductURL, request,
				ProductApiResponse.class);
		// Product addedProduct = prodRepo.save(product);
		ProductApiResponse productApiResponse = apiResponse.getBody();
		if (apiResponse.getStatusCode() != HttpStatus.CREATED) {
			response = "Product - " + product.getName() + " could not be added !!";
		} else {
			response = "Product - " + product.getName() + " was successfully added. Product Id - "
					+ productApiResponse.productId;
		}
		model.addAttribute("response", response);
		return "productadded";
	}
}

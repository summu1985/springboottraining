package com.sanaari.springboot.training.jsonjpademo;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanaari.springboot.training.jsonjpademo.domain.Product;
import com.sanaari.springboot.training.jsonjpademo.service.ProductAPIService;

@SpringBootApplication
public class JsonJpaDemoApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(JsonJpaDemoApplication.class);

	@Autowired
	ProductAPIService productService;

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(JsonJpaDemoApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {
			};
			InputStream inputStream = JsonJpaDemoApplication.class.getResourceAsStream("/products.json");

			List<Product> products = mapper.readValue(inputStream, typeReference);
			products.stream().forEach(p -> {
				productService.addProduct(p);
				LOG.info("saved product: " + p);
			});
			LOG.info("Products Saved!");
		} catch (Exception e) {
			LOG.error("Unable to save Products: " + e.getMessage());
		}
	}
}

package com.sanaari.springboot.training.jsonjpademo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanaari.springboot.training.jsonjpademo.domain.Product;
import com.sanaari.springboot.training.jsonjpademo.repositories.ProductRepository;

@Service
public class ProductAPIServiceImpl implements ProductAPIService {

	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = (List<Product>) prodRepo.findAll();
		products.sort((p1,p2)->p1.getProductId()-p2.getProductId());
		return products;
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
	public int deleteProduct(int id) {
		// TODO Auto-generated method stub
		int status = -1;
		if (prodRepo.findById(id).isPresent()) {
			try {
				prodRepo.deleteById(id);
				status = 0;
			} catch (Exception ex) {
				status = 1;
			}
		} else {
			status = -1;
		}

		return status;
	}

	@Override
	public int updateProduct(int id, Product product) {
		// TODO Auto-generated method stub
		int status = -1;
		try {
			Optional<Product> productToUpdate = prodRepo.findById(id);
			if (productToUpdate.isPresent()) {
				productToUpdate.get().setDescription(product.getDescription());
				productToUpdate.get().setName(product.getName());
				productToUpdate.get().setQuantity(product.getQuantity());
				productToUpdate.get().setRating(product.getRating());
				prodRepo.save(productToUpdate.get());
				status = 0;
			}
		} catch (Exception ex) {
			status = 1;
		}
		return status;
	}

}

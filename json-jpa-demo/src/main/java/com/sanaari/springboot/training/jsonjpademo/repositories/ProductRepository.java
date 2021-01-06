package com.sanaari.springboot.training.jsonjpademo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sanaari.springboot.training.jsonjpademo.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

	void deleteById(int id);

}

package com.sanaari.springboot.training.crmwebapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sanaari.springboot.training.crmwebapp.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

	void deleteById(int id);

}

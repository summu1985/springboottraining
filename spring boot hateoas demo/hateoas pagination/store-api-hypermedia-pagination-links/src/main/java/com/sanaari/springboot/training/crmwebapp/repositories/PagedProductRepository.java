package com.sanaari.springboot.training.crmwebapp.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sanaari.springboot.training.crmwebapp.domain.Product;

public interface PagedProductRepository extends PagingAndSortingRepository<Product, Long>{

}

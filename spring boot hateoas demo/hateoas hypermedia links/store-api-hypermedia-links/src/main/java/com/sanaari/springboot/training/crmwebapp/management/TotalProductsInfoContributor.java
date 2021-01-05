package com.sanaari.springboot.training.crmwebapp.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.service.ProductAPIService;

@Component
public class TotalProductsInfoContributor implements InfoContributor{

	@Autowired
	private ProductAPIService prodService;
	
	@Override
	public void contribute(Builder builder) {
		// TODO Auto-generated method stub
		long totalProducts = prodService.getAllProducts().stream().count();
		List<Product> products = prodService.getAllProducts();
		builder.withDetail("total products", totalProducts).withDetail("products", products);
	}

}

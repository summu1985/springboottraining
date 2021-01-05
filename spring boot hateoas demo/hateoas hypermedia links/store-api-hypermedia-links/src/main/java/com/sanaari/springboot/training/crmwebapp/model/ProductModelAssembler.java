package com.sanaari.springboot.training.crmwebapp.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sanaari.springboot.training.crmwebapp.controllers.ProductAPIController;
import com.sanaari.springboot.training.crmwebapp.domain.Product;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel>{

	public ProductModelAssembler() {
		super(ProductAPIController.class, ProductModel.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ProductModel toModel(Product entity) {
		// TODO Auto-generated method stub
		ProductModel productModel = instantiateModel(entity);
		Link link = linkTo(methodOn(ProductAPIController.class).getProductById(entity.getProductId())).withSelfRel();
		productModel.add(link);
		productModel.setDescription(entity.getDescription());
		productModel.setName(entity.getName());
		productModel.setProductId(entity.getProductId());
		productModel.setQuantity(entity.getQuantity());
		productModel.setRating(entity.getRating());
		
		Link allProductsLink = linkTo(methodOn(ProductAPIController.class).getAllProducts()).withRel("allProducts");
		productModel.add(allProductsLink);
		
		return productModel;
	}
	
	@Override
	public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends Product> entities) {
		CollectionModel<ProductModel> productModels = super.toCollectionModel(entities);
        
		productModels.add(linkTo(methodOn(ProductAPIController.class).getAllProducts()).withSelfRel());
         
        return productModels;
	}

}

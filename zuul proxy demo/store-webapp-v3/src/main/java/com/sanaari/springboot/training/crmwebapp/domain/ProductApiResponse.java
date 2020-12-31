package com.sanaari.springboot.training.crmwebapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductApiResponse {
	public String message;
	public String status;
	public int productId;
	
	public ProductApiResponse(String message, String status, int productId) {
		this.message = message;
		this.status = status;
		this.productId = productId;
	}

	public ProductApiResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
}

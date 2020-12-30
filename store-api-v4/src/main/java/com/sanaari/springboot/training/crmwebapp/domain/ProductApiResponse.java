package com.sanaari.springboot.training.crmwebapp.domain;

public class ProductApiResponse {
	String message;
	String status;
	Integer productId;
	
	public ProductApiResponse(String message, String status, Integer productId) {
		this.message = message;
		this.status = status;
		this.productId = productId;
	}

	public ProductApiResponse() {
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}

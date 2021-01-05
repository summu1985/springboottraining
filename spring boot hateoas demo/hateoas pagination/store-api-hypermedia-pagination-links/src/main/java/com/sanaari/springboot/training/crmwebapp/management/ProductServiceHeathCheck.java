package com.sanaari.springboot.training.crmwebapp.management;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceHeathCheck extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		// TODO Auto-generated method stub
		//builder.down(new Exception("Some exception occured in Product Service")).build();
		// Logic to evaluate health check.
		//int a = 0; int b= 0;
		//int c = a/b;
		builder.up().withDetail("status", "Product Service running fine").build();
	}

}

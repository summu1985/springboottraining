package com.sanaari.springboot.training.crmwebapp.batch;

import org.springframework.batch.item.ItemProcessor;

public class SimpleJobItemProcessor implements ItemProcessor<String,String>{

	@Override
	public String process(String item) throws Exception {
		// TODO Auto-generated method stub
		return item.toUpperCase();
	}

}

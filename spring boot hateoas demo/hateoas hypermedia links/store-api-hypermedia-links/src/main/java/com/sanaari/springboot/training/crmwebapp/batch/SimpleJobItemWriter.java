package com.sanaari.springboot.training.crmwebapp.batch;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.batch.item.ItemWriter;

public class SimpleJobItemWriter implements ItemWriter<String>{

	Logger logger = Logger.getLogger(SimpleJobItemWriter.class);
	@Override
	public void write(List<? extends String> items) throws Exception {
		// TODO Auto-generated method stub
		for(String item : items) {
			logger.info("Writing data : " + item);
		}
	}

}

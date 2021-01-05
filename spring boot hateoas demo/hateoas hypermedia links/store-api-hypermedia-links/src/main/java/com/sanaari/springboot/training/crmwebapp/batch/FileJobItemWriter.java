package com.sanaari.springboot.training.crmwebapp.batch;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.batch.item.ItemWriter;

import com.sanaari.springboot.training.crmwebapp.domain.Product;

public class FileJobItemWriter implements ItemWriter<Product>{

	Logger logger = Logger.getLogger(FileJobItemWriter.class);
	@Override
	public void write(List<? extends Product> items) throws Exception {
		for (Product item : items) {
			logger.info("Writing Product : " + item);
		}
		
	}

}

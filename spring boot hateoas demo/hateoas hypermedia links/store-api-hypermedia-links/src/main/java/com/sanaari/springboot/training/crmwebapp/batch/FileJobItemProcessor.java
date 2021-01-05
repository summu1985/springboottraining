package com.sanaari.springboot.training.crmwebapp.batch;

import org.jboss.logging.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.sanaari.springboot.training.crmwebapp.domain.Product;

public class FileJobItemProcessor implements ItemProcessor<Product,Product>{
	
	Logger logger = Logger.getLogger(FileJobItemProcessor.class);
	@Override
	public Product process(Product item) throws Exception {
		// TODO Auto-generated method stub
		Product processedProduct = item;
		logger.info("Processed product : " + processedProduct);
		return processedProduct;
	}
}



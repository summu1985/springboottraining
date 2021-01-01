package com.sanaari.springboot.training.crmwebapp.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import com.sanaari.springboot.training.crmwebapp.domain.Product;

public class FileJobItemReader extends FlatFileItemReader<Product> {

	private String feedFile="D:\\personal\\freelance\\code\\java\\springboot\\products.csv";

	public FileJobItemReader() {
		setResource(new FileSystemResource(feedFile));
		setLineMapper(new ProductLineMapper());
	}

}

class ProductLineMapper extends DefaultLineMapper<Product> {
	public ProductLineMapper() {
		setLineTokenizer(new ProductDelimitedLineTokenizer());
		setFieldSetMapper(new ProductFieldSetMapper());
		// setFieldSetMapper(new ProductFieldSetMapper());
	}
}

class ProductDelimitedLineTokenizer extends DelimitedLineTokenizer {
	public ProductDelimitedLineTokenizer() {
		setNames(new String[] { "productId", "name", "description", "rating", "quantity" });
	}
}

class ProductFieldSetMapper implements FieldSetMapper<Product> {

	@Override
	public Product mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		if (fieldSet == null) {
			return null;
		}

		Product product = new Product();
		product.setDescription(fieldSet.readString("description"));
		product.setName(fieldSet.readString("name"));
		product.setProductId(fieldSet.readInt("productId"));
		product.setQuantity(fieldSet.readInt("quantity"));
		product.setRating(fieldSet.readDouble("rating"));

		return product;
	}

}

package com.sanaari.springboot.training.crmwebapp.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class SimpleJobItemReader implements ItemReader<String>{

	String[] messages = {"This is Spring boot training","This is spring batch demo","This is Simple batch job example"};
	private int count = 0;
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		return null;
	}
	

}

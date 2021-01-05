package com.sanaari.springboot.training.crmwebapp.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sanaari.springboot.training.crmwebapp.domain.Product;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}
	
	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
				.reader(new SimpleJobItemReader()).processor(new SimpleJobItemProcessor())
				.writer(new SimpleJobItemWriter()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new SimpleJobCompletionListener();
	}
	
	@Bean Job fileProcessJob() {
		return jobBuilderFactory.get("fileProcessJob").incrementer(new RunIdIncrementer()).listener(listener()).flow(fileProcessStep()).end().build();
	}
	
	@Bean
	   public Step fileProcessStep() {
	      return stepBuilderFactory.get("fileProcessStep").<Product, Product>chunk(10).reader(new FileJobItemReader()).processor(new FileJobItemProcessor()).writer(new FileJobItemWriter()).build();
	   }
}

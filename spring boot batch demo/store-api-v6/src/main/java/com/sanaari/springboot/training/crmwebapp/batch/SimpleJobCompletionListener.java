package com.sanaari.springboot.training.crmwebapp.batch;

import org.jboss.logging.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class SimpleJobCompletionListener extends JobExecutionListenerSupport {
	Logger logger = Logger.getLogger(SimpleJobCompletionListener.class);
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}
}

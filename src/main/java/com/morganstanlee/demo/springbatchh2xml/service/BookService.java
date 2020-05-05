package com.morganstanlee.demo.springbatchh2xml.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morganstanlee.demo.springbatchh2xml.model.Book;
import com.morganstanlee.demo.springbatchh2xml.repo.BookRepository;

@Service
public class BookService {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
	@Autowired
	private BookRepository bookRepository;
	
	public BatchStatus writeToXml() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> parameters = new HashMap<>();

		parameters.put("time", new JobParameter(System.currentTimeMillis()));

		JobParameters jobParameters = new JobParameters(parameters);

		JobExecution jobExecution = jobLauncher.run(job, jobParameters);

		while (jobExecution.isRunning()) {
			System.out.println("......");
		}
		
		return jobExecution.getStatus();
	}
	
	public List<Book> writeToDb(List<Book> books) {
		 return bookRepository.saveAll(books);
		 
	}
}

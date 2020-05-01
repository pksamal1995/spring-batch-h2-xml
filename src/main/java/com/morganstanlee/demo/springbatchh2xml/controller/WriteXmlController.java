package com.morganstanlee.demo.springbatchh2xml.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morganstanlee.demo.springbatchh2xml.service.BookService;

@RestController
@RequestMapping("/write")
public class WriteXmlController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/xml")
	public BatchStatus writeToXml() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		return bookService.writeToXml();
	}
}

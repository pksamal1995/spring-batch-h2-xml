package com.morganstanlee.demo.springbatchh2xml.controller;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morganstanlee.demo.springbatchh2xml.model.Book;
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
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> writeToDb(@RequestBody List<Book> books) {
		return new ResponseEntity<List<Book>>(bookService.writeToDb(books), HttpStatus.CREATED);
	}
}

package com.morganstanlee.demo.springbatchh2xml.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.morganstanlee.demo.springbatchh2xml.model.Book;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private StaxEventItemWriter<Book> writer;
	
	@Bean
	public Job job() {
		
		return jobBuilderFactory.get("BOOK PRINT")
				.incrementer(new RunIdIncrementer())
				.start(step())
				.build();
	}
	private Step step() {
		return stepBuilderFactory.get("DB-CONSOLE")
				.<Book, Book>chunk(50)
				.reader(reader())
				.writer(writer)
				.build();
	}
	
	@Bean
	@StepScope
	public JdbcCursorItemReader<Book> reader(){
		JdbcCursorItemReader<Book> itemReader = new JdbcCursorItemReader<>();
		itemReader.setDataSource(dataSource);
		itemReader.setSql("select id, isbn, name, author, publisher, genere, published_year from book");
		itemReader.setRowMapper(new RowMapper<Book>() {		
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublishedYear(rs.getInt("published_year"));
				
				return book;
			}
		});
		
		return itemReader;
	}
	
	
	@Bean
	public StaxEventItemWriter<Book> itemWriter(@Value("${book}") Resource outputResource) {
		return new StaxEventItemWriterBuilder<Book>()
				.name("booksWriter")
				.marshaller(booksMarshaller())
				.resource(outputResource)
				.rootTagName("book")
				.overwriteOutput(true)
				.build();

	}
	private Jaxb2Marshaller booksMarshaller() {	
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(Book.class);
		return jaxb2Marshaller;
	}
	
}

package com.morganstanlee.demo.springbatchh2xml.model;

public class BookDto {

	private Integer id;
	private String isbn;
	private String name;
	private String author;
	private String publisher;
	private String genere;
	private Integer publishedYear;

	public BookDto() {

	}

	public BookDto(Integer id, String isbn, String name, String author, String publisher, String genere,
			Integer publishedYear) {

		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.genere = genere;
		this.publishedYear = publishedYear;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public Integer getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(Integer publishedYear) {
		this.publishedYear = publishedYear;
	}


}

package com.morganstanlee.demo.springbatchh2xml.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morganstanlee.demo.springbatchh2xml.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

}

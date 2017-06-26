package com.pdamjanovic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pdamjanovic.entities.Book;

public interface BookService {

	Book save(Book book);

	void delete(Book book);

	void deleteAll();

	Book findOne(Long id);

	Iterable<Book> findAll();

	Page<Book> findByAuthor(String author, PageRequest pageRequest);

	List<Book> findByTitle(String title);

	Book findById(Long id);

}

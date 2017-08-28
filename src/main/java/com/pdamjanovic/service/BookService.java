package com.pdamjanovic.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pdamjanovic.entities.Book;

public interface BookService {

	Book save(Book book);

	void delete(Book book);

	void deleteById(Long id);

	void deleteAll();

	Iterable<Book> findAll();

	Page<Book> findByAuthor(String author, PageRequest pageRequest);

	List<Book> findByTitle(String title);

	Book findById(Long id);

	Page<Book> search(String title);

	Page<Book> search(QueryBuilder queryBuilder);

	Page<Book> searchSimilar(Long bookId);
}

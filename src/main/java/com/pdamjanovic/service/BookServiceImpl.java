package com.pdamjanovic.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.repositories.elasticsearch.BookESRepository;
import com.pdamjanovic.repositories.jpa.BookJPARepository;

@Service
public class BookServiceImpl implements BookService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private BookESRepository bookRepositoryIR;
	private BookJPARepository bookRepository;

	@Autowired
	public void setBookRepository(BookJPARepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Autowired
	public void setBookRepositoryIR(BookESRepository bookRepositoryIR) {
		this.bookRepositoryIR = bookRepositoryIR;
	}

	public Book save(Book book) {
		book = bookRepository.save(book);
		bookRepositoryIR.save(book);
		return book;
	}

	public void delete(Book book) {
		bookRepositoryIR.delete(book);
		bookRepository.delete(book);
	}

	public void deleteById(Long id) {
		bookRepositoryIR.delete(id);
		bookRepository.delete(id);
	}

	public void deleteAll() {
		bookRepositoryIR.deleteAll();
		bookRepository.deleteAll();
	}

	public Iterable<Book> findAll() {
		return bookRepository.findAll();
	}

	public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
		return bookRepository.findByAuthor(author, pageRequest);
	}

	public List<Book> findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}

	public Book findById(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public Page<Book> search(String title) {

		SearchQuery matchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("title", title)).build();
		Page<Book> matchQueryResult = bookRepositoryIR.search(matchQuery);
		log.info("matchQueryResult: " + matchQueryResult);

		return matchQueryResult;
	}

	@Override
	public Page<Book> search(QueryBuilder queryBuilder) {

		SearchQuery matchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
		Page<Book> matchQueryResult = bookRepositoryIR.search(matchQuery);
		log.info("matchQueryResult: " + queryBuilder.getClass() + ", resulted in: " + matchQueryResult);

		return matchQueryResult;
	}

}

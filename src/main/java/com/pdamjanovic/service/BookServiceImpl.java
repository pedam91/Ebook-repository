package com.pdamjanovic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.repositories.elasticsearch.BookESRepository;

@Service
public class BookServiceImpl implements BookService {

	private BookESRepository bookRepository;

	@Autowired
	public void setBookRepository(BookESRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public void delete(Book book) {
		bookRepository.delete(book);
	}

	public void deleteAll() {
		bookRepository.deleteAll();
	}

	public Book findOne(Long id) {
		return bookRepository.findOne(id);
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

}

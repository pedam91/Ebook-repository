package com.pdamjanovic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.repositories.jpa.BookJPARepository;

@Service
public class BookServiceImpl implements BookService {

//	private BookESRepository bookRepository;
	private BookJPARepository bookRepository;

	@Autowired
	public void setBookRepository(BookJPARepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public void delete(Book book) {
		bookRepository.delete(book);
	}

	public void deleteById(Long id) {
		bookRepository.delete(id);
	}

	public void deleteAll() {
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

}

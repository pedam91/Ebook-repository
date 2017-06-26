package com.pdamjanovic.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.service.BookService;

@Controller
public class BookController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BookService bookService;

	@GetMapping("/book/{id}")
	public String getBookById(@PathVariable("id") Long id, Map<String, Object> model) {

		logger.info("Get book by id:" + id);

		Book book = bookService.findById(id);
		model.put("book", book);

		return "book";
	}
}

package com.pdamjanovic.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.entities.UserRoles;
import com.pdamjanovic.service.BookService;
import com.pdamjanovic.service.CategoryService;
import com.pdamjanovic.service.LanguageService;
import com.pdamjanovic.service.UserService;
import com.pdamjanovic.util.LoggedInUser;

@Controller
public class BookController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	LanguageService languageService;

	@Autowired
	UserService userService;

	@GetMapping("/book/{id}")
	public String getBookPage(@PathVariable("id") Long id, Map<String, Object> model) {

		logger.info("Get book by id:" + id);

		Book book = bookService.findById(id);
		model.put("book", book);

		return "book";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping(value = "/book/create")
	public String getCreateBookPage(Map<String, Object> model) {

		logger.info("Create book page called");

		model.put("book", new Book());
		model.put("categories", categoryService.findAll());
		model.put("languages", languageService.findAll());
		model.put("infoMessage", "Please enter information about new book:");

		return "book_edit";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@PostMapping(value = "/book/create")
	public String createBook(@Validated @ModelAttribute("book") Book book, BindingResult errors,
			Map<String, Object> model) {

		if (errors.hasErrors()) {
			model.put("errorMessage", "Please correct form errors.");
			return "book_edit";
		}

		LoggedInUser loggedInUser = (LoggedInUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		book.setCataloguer(userService.findById(loggedInUser.getId()));

		Book newBook = bookService.save(book);
		model.put("book", newBook);

		model.put("successMessage", "Book info successfully created.");

		return "book";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping(value = "/book/{id}/edit")
	public String getEditBookPage(@PathVariable("id") Long bookId, Map<String, Object> model) {

		logger.info("Edit book by id:" + bookId);

		Book book = bookService.findById(bookId);
		model.put("book", book);

		model.put("categories", categoryService.findAll());
		model.put("languages", languageService.findAll());

		return "book_edit";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@PostMapping(value = "/book/{id}/edit")
	public String editBook(@Validated @ModelAttribute("book") Book book, BindingResult errors,
			@PathVariable("id") Long bookId, Map<String, Object> model) {

		logger.info("Edit book (POST) by id:" + bookId);

		if (errors.hasErrors()) {
			model.put("errorMessage", "Please correct form errors.");
			return "book_edit";
		}

		Book updated = bookService.save(book);
		model.put("book", updated);

		model.put("successMessage", "Book info successfully updated.");

		return "book";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping(value = "/book/{id}/delete")
	public String deleteBookById(@PathVariable("id") Long bookId, RedirectAttributes redirectAttributes) {

		logger.info("Delete book by id:" + bookId);

		bookService.deleteById(bookId);

		redirectAttributes.addFlashAttribute("successMessage", "Book id: [" + bookId + "] succesfully deleted.");
		return "redirect:/";
	}

}

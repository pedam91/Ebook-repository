package com.pdamjanovic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.entities.BookFile;
import com.pdamjanovic.entities.Category;
import com.pdamjanovic.entities.Language;
import com.pdamjanovic.entities.UserRoles;
import com.pdamjanovic.service.BookFileService;
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
	BookFileService bookFileService;

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
			Map<String, Object> model, @RequestParam("uploadfiles") Part[] files) throws IOException {

		if (errors.hasErrors()) {
			model.put("errorMessage", "Please correct form errors.");
			model.putIfAbsent("categories", categoryService.findAll());
			model.putIfAbsent("languages", languageService.findAll());
			return "book_edit";
		}

		LoggedInUser loggedInUser = (LoggedInUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		book.setCataloguer(userService.findById(loggedInUser.getId()));

		List<BookFile> bookFiles = new ArrayList<>();
		for (Part uploadedFile : files) {
			BookFile bookFile = new BookFile(uploadedFile);
			bookFiles.add(bookFile);

			String fileName = uploadedFile.getSubmittedFileName();

			if (!fileName.endsWith("pdf")) {
				model.put("errorMessage", "Only PDF file type is allowed.");
				model.putIfAbsent("categories", categoryService.findAll());
				model.putIfAbsent("languages", languageService.findAll());
				return "book_edit";
			}

			File docsFolder = new ClassPathResource("docs").getFile();
			// TODO handle Serbian characters in the name?
			fileName = System.currentTimeMillis() + "_" + fileName;
			bookFile.setStoredFileName(fileName);

			File outputFile = new File(docsFolder + File.separator + fileName);

			try (InputStream filecontent = uploadedFile.getInputStream();
					OutputStream out = new FileOutputStream(outputFile);) {
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			} catch (IOException ioException) {
				logger.error("Error during file upload", ioException);
			}

			// ************ PDF PARSER ****************
			PDFParser parser = new PDFParser(uploadedFile.getInputStream());
			parser.parse();
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();
			String author = info.getAuthor();
			String title = info.getTitle();
			String keywords = info.getKeywords();
			logger.info("PDF author: " + author + ", title:" + title + ", keywords: " + keywords);

			PDFTextStripper stripper = new PDFTextStripper("utf-8");
			String text = stripper.getText(pdf);
			if (text != null && !text.trim().equals("")) {
				logger.info("Text length: " + text.length());
			}
			bookFile.setContent(text);
			pdf.close();
		}

		book.setFiles(bookFiles);

		Book newBook = bookService.save(book);
		model.put("book", newBook);

		model.put("successMessage", "Book [" + book.getTitle() + "] info successfully created.");

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

	@GetMapping("/book/download/{fileId}")
	public void downloadBookFile(@PathVariable("fileId") Long fileId, HttpServletResponse response) {

		logger.info("Download book file id: {}", fileId);

		BookFile bookFile = bookFileService.findById(fileId);
		String fileName = bookFile.getStoredFileName();

		// You must tell the browser the file type you are going to send
		// for example application/pdf, text/plain, text/html, image/jpg
		response.setContentType("application/pdf");

		// Make sure to show the download dialog
		response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

		try {
			File fileOnDisk = new ClassPathResource("docs" + File.separator + fileName).getFile();
			response.setContentLengthLong(fileOnDisk.length());
			// This should send the file to browser
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(fileOnDisk);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
		} catch (IOException e) {
			logger.error("Error during book file download: {}", fileId, e);
		}
	}

	@PostConstruct
	public void testData() {
		Book book = new Book("Koreni", "Dobrica Cosic", "ninova nagrada", "1954");
		book.setLanguage(new Language(2L, "Serbian"));
		book.setCategory(new Category(1L, "History"));
		BookFile bookFile = new BookFile();
		bookFile.setFileName("Primer.pdf");
		bookFile.setMime("application/pdf");
		bookFile.setStoredFileName("primer.pdf");
		book.setFiles(Arrays.asList(bookFile));
		bookService.save(book);
	}

}

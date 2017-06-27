package com.pdamjanovic.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.pdamjanovic.EbooksApplication;
import com.pdamjanovic.entities.Book;
import com.pdamjanovic.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EbooksApplication.class)
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Before
	public void before() {
		esTemplate.deleteIndex(Book.class);
		esTemplate.createIndex(Book.class);
		esTemplate.putMapping(Book.class);
		esTemplate.refresh(Book.class);
	}

	@Test
	public void testSave() {

		Book book = new Book("Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004");
		Book testBook = bookService.save(book);

		assertNotNull(testBook.getId());
		assertEquals(testBook.getTitle(), book.getTitle());
		assertEquals(testBook.getAuthor(), book.getAuthor());
		assertEquals(testBook.getReleaseDate(), book.getReleaseDate());

		bookService.delete(testBook);
	}

	@Test
	public void testFindOne() {

		Book savedBook = bookService.save(new Book("Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004"));

		Book testBook = bookService.findById(savedBook.getId());

		assertNotNull(testBook.getId());
		assertEquals(testBook.getTitle(), savedBook.getTitle());
		assertEquals(testBook.getAuthor(), savedBook.getAuthor());
		assertEquals(testBook.getReleaseDate(), savedBook.getReleaseDate());

		bookService.delete(testBook);
	}

	@Test
	public void testFindByTitle() {

		Book testBook = new Book("Elasticsearch Basics-unique-title", "Rambabu Posa", "keywords1", "2004");
		bookService.save(testBook);

		List<Book> byTitle = bookService.findByTitle(testBook.getTitle());
		assertThat(byTitle.size(), is(1));
		bookService.delete(testBook);
	}

	@Test
	public void testFindByAuthor() {

		List<Book> bookList = new ArrayList<>();
		
		String author = "Rambabu Posa-author";
		String keywords = "keyword";
		String publicationYear = "2004";

		bookList.add(new Book("Elasticsearch Basics", author, keywords, publicationYear));
		bookList.add(new Book("Apache Lucene Basics", author, keywords, publicationYear));
		bookList.add(new Book("Apache Solr Basics", author, keywords, publicationYear));
		bookList.add(new Book("Spring Data + ElasticSearch", author, keywords, publicationYear));
		bookList.add(new Book("Spring Boot + MongoDB", "Mkyong", keywords, publicationYear));

		for (Book book : bookList) {
			bookService.save(book);
		}

		Page<Book> byAuthor = bookService.findByAuthor(author, new PageRequest(0, 10));
		assertThat(byAuthor.getTotalElements(), is(equalTo(4L)));

		Page<Book> byAuthor2 = bookService.findByAuthor("Mkyong", new PageRequest(0, 10));
		assertThat(byAuthor2.getTotalElements(), is(1L));

		for (Book book : bookList) {
			bookService.delete(book);
		}
	}

	@Test
	public void testDelete() {

		Book book = new Book("Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004");
		bookService.save(book);
		bookService.delete(book);
		Book testBook = bookService.findById(book.getId());
		assertNull(testBook);
	}

}
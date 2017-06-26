package com.pdamjanovic.test;

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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

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

		Book book = new Book(1001L, "Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004");
		Book testBook = bookService.save(book);

		assertNotNull(testBook.getId());
		assertEquals(testBook.getTitle(), book.getTitle());
		assertEquals(testBook.getAuthor(), book.getAuthor());
		assertEquals(testBook.getReleaseDate(), book.getReleaseDate());

	}

	@Test
	public void testFindOne() {

		Book book = new Book(1001L, "Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004");
		bookService.save(book);

		Book testBook = bookService.findOne(book.getId());

		assertNotNull(testBook.getId());
		assertEquals(testBook.getTitle(), book.getTitle());
		assertEquals(testBook.getAuthor(), book.getAuthor());
		assertEquals(testBook.getReleaseDate(), book.getReleaseDate());

	}

	@Test
	public void testFindByTitle() {

		Book book = new Book(1001L, "Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004");
		bookService.save(book);

		List<Book> byTitle = bookService.findByTitle(book.getTitle());
		assertThat(byTitle.size(), is(1));
	}

	@Test
	public void testFindByAuthor() {

		List<Book> bookList = new ArrayList<>();

		bookList.add(new Book(1001L, "Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004"));
		bookList.add(new Book(1002L, "Apache Lucene Basics", "Rambabu Posa", "keywords1", "2004"));
		bookList.add(new Book(1003L, "Apache Solr Basics", "Rambabu Posa", "keywords1", "2004"));
		bookList.add(new Book(1004L, "Spring Data + ElasticSearch", "Rambabu Posa", "keywords1", "2004"));
		bookList.add(new Book(1005L, "Spring Boot + MongoDB", "Mkyong", "keywords1", "2004"));

		for (Book book : bookList) {
			bookService.save(book);
		}

		Page<Book> byAuthor = bookService.findByAuthor("Rambabu Posa", new PageRequest(0, 10));
		assertThat(byAuthor.getTotalElements(), is(equalTo(4L)));

		Page<Book> byAuthor2 = bookService.findByAuthor("Mkyong", new PageRequest(0, 10));
		assertThat(byAuthor2.getTotalElements(), is(1L));

	}

	@Test
	public void testDelete() {

		Book book = new Book(1001L, "Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004");
		bookService.save(book);
		bookService.delete(book);
		Book testBook = bookService.findOne(book.getId());
		assertNull(testBook);
	}

}
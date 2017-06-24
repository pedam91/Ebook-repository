package com.pdamjanovic;

import java.util.List;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.service.BookService;

@SpringBootApplication
public class EbooksApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(EbooksApplication.class);

	@Autowired
    private ElasticsearchOperations es;

    @Autowired
    private BookService bookService;

    public static void main(String args[]) {
        SpringApplication.run(EbooksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfo();

		// bookService.deleteAll();
        Book book1 = new Book(1001L, "Elasticsearch Basics", "Rambabu Posa", "keywords1", "2004");
        bookService.save(book1);
        bookService.save(new Book(1002L, "Apache Lucene Basics", "Rambabu Posa", "keywords2", "2004"));
        bookService.save(new Book(1003L, "Apache Solr Basics", "Rambabu Posa", "keywords3", "2004"));
        
        //fuzzy search
        Page<Book> booksByAuthor = bookService.findByAuthor("Rambabu", new PageRequest(0, 10));
        logger.info("Books search by author results:");
        booksByAuthor.forEach(x -> logger.info(x.toString()));

        List<Book> booksByTitle = bookService.findByTitle("Elasticsearch Basics");
        logger.info("Books search by title results:");
        booksByTitle.forEach(x -> logger.info(x.toString()));



    }

    //useful for debug, print elastic search details
	private void printElasticSearchInfo() {

		logger.info("--ElasticSearch--");
		Client client = es.getClient();
		Map<String, String> asMap = client.settings().getAsMap();

		asMap.forEach((k, v) -> {
			logger.info(k + " = " + v);
		});
		logger.info("--ElasticSearch--");
	}

}

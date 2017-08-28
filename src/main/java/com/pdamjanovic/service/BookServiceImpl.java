package com.pdamjanovic.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
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
	private ElasticsearchTemplate template;

	@Autowired
	public void setBookRepository(BookJPARepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Autowired
	public void setBookRepositoryIR(BookESRepository bookRepositoryIR) {
		this.bookRepositoryIR = bookRepositoryIR;
	}

	@Autowired
	public void setTemplate(ElasticsearchTemplate template) {
		this.template = template;
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

	@Override
	public Page<Book> searchWithHighlights(QueryBuilder queryBuilder) {

		SearchQuery matchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder)
				.withHighlightFields(new Field("title"), new Field("files.content")).build();

		Page<Book> sampleEntities = template.queryForPage(matchQuery, Book.class, new SearchResultMapper() {
			@Override
			public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<Book> chunk = new ArrayList<>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					Book book = new Book();
					book.setId(Long.parseLong(searchHit.getSource().get("id").toString()));
					book.setTitle(searchHit.getSource().get("title").toString());
					book.setHighlightedMessages(searchHit.getHighlightFields());
					chunk.add(book);
				}

				if (chunk.size() > 0) {
					return new AggregatedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}
		});

		log.info("sampleEntities: " + queryBuilder.getClass() + ", resulted in: " + sampleEntities);
		return sampleEntities;
	}

	public Page<Book> searchSimilar(Long bookId) {
		// can't set min freq here
		// Book book = bookRepositoryIR.findOne(bookId);
		// Page<Book> similarSearchResult = bookRepositoryIR.searchSimilar(book, new
		// String[] { "title", "files.content" },
		// Query.DEFAULT_PAGE);

		MoreLikeThisQuery query = new MoreLikeThisQuery();
		query.setId(bookId.toString());
		query.setMinDocFreq(0);
		query.setMinTermFreq(0);
		Page<Book> similarSearchResult = template.moreLikeThis(query, Book.class);

		log.info("searchSimilar [" + bookId + "] resulted in: " + similarSearchResult);
		return similarSearchResult;
	}
}

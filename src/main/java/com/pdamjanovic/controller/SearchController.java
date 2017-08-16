package com.pdamjanovic.controller;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.BooleanClause.Occur;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pdamjanovic.entities.Book;
import com.pdamjanovic.service.BookService;
import com.pdamjanovic.util.OccurTypes;
import com.pdamjanovic.util.SearchType;

@Controller
public class SearchController {

	@Autowired
	BookService bookService;

	@GetMapping("/search")
	public String getSearchPage(Map<String, Object> model) {

		List<String> occures = new ArrayList<String>();
		for (Occur o : Occur.values()) {
			occures.add(o.toString());
		}
		model.put("occures", occures);
		model.put("searchTypes", SearchType.getMessages());

		return "search";
	}

	@PostMapping("/search*")
	public String search(Map<String, Object> model, HttpServletRequest request) {

		Page<Book> searchResults = null;
		QueryBuilder queryBuilder = null;
		BoolQueryBuilder bquery = boolQuery();

		String title = request.getParameter("title");
		String titlest = request.getParameter("titlest");
		SearchType.Type titleSearchType = SearchType.getType(titlest);
		String titlesc = request.getParameter("titlesc");
		Occur titleOccur = OccurTypes.getOccur(titlesc);

		if (!(title == null || title.equals(""))) {
			queryBuilder = createQuery(titleSearchType, "title", title);
			addOccur(titleOccur, bquery, queryBuilder);
		}

		searchResults = bookService.search(bquery);
		model.put("searchResults", searchResults.getContent());

		return "search_results";
	}

	private QueryBuilder createQuery(SearchType.Type searchType, String fieldName, String fieldValue) {

		QueryBuilder retVal = null;

		switch (searchType) {
		case fuzzy: {
			// maxEdits = 1 ?
			retVal = fuzzyQuery(fieldName, fieldValue);
			break;
		}
		case phrase: {
			retVal = matchPhraseQuery(fieldName, fieldValue);
			break;
		}
		case prefix: {
			retVal = prefixQuery(fieldName, fieldValue);
			break;
		}
		case range: {
			retVal = rangeQuery(fieldName);
			break;
		}
		case regular: {
			// should be termQuery ?
			retVal = matchQuery(fieldName, fieldValue);
			break;
		}
		}

		return retVal;
	}

	private void addOccur(Occur occur, BoolQueryBuilder boolQuery, QueryBuilder newQueryPart) {
		switch (occur) {
		case MUST:
			boolQuery.must(newQueryPart);
			break;
		case MUST_NOT:
			boolQuery.mustNot(newQueryPart);
			break;
		case FILTER:
			boolQuery.filter(newQueryPart);
			break;
		case SHOULD:
			boolQuery.should(newQueryPart);
			break;
		}
	}
}

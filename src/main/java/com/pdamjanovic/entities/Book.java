package com.pdamjanovic.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.elasticsearch.search.highlight.HighlightField;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(indexName = "ebooks", type = "books")
@JsonIgnoreProperties(value = { "category", "cataloguer" })
@Entity
public class Book extends AbstractEntity {

	@Column
	@NotBlank
	@Field(type = FieldType.String, analyzer = "sr_analyzer")
	private String title;

	@Column
	@Size(min = 2, max = 50)
	@Field(type = FieldType.String)
	private String author;

	@Column
	@Field(type = FieldType.String, analyzer = "sr_analyzer")
	private String keywords;

	@Column
	@Field(type = FieldType.String)
	private String publicationYear;

	@ManyToOne(optional = false)
	@Field(type = FieldType.Nested)
	private Language language;

	@ManyToOne(optional = false)
	private Category category;

	@ManyToOne
	private User cataloguer;

	@OneToMany(cascade = CascadeType.ALL)
	@Field(type = FieldType.Nested)
	private List<BookFile> files;

	@Transient
	private Map<String, HighlightField> highlightedMessages;

	public Book() {
	}

	public Book(String title, String author, String keywords, String publicationYear) {
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.publicationYear = publicationYear;
		this.files = new ArrayList<>();
	}

	public Book(String title, String author, String keywords, String publicationYear, Language language,
			Category category, User cataloguer, List<BookFile> files) {
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.publicationYear = publicationYear;
		this.language = language;
		this.category = category;
		this.cataloguer = cataloguer;
		this.files = files;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReleaseDate() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getKeywords() {
		return keywords;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getCataloguer() {
		return cataloguer;
	}

	public void setCataloguer(User cataloguer) {
		this.cataloguer = cataloguer;
	}

	public List<BookFile> getFiles() {
		return files;
	}

	public void setFiles(List<BookFile> files) {
		this.files = files;
	}

	public Map<String, HighlightField> getHighlightedMessages() {
		return this.highlightedMessages;
	}

	public void setHighlightedMessages(Map<String, HighlightField> highlightedMessages) {
		this.highlightedMessages = highlightedMessages;
	}

	@Override
	public String toString() {
		return "Book{" + "id='" + super.getId() + '\'' + ", title='" + title + '\'' + ", author='" + author + '\''
				+ ", keywords='" + keywords + '\'' + ", publicationYear='" + publicationYear + '\'' + '}';
	}

}

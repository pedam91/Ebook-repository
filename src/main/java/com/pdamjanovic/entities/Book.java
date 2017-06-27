package com.pdamjanovic.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ebooks", type = "books")
@Entity
public class Book extends AbstractEntity {

	@Column
	private String title;

	@Column
	private String author;

	@Column
	private String keywords;

	@Column
	private String publicationYear;

	@ManyToOne
	private Language language;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User cataloguer;

	@OneToMany
	private List<File> files;

	public Book() {
	}

	public Book(String title, String author, String keywords, String publicationYear) {
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.publicationYear = publicationYear;
	}

	public Book(String title, String author, String keywords, String publicationYear, Language language,
			Category category, User cataloguer, List<File> files) {
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

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Book{" + "id='" + super.getId() + '\'' + ", title='" + title + '\'' + ", author='" + author + '\''
				+ ", keywords='" + keywords + '\'' + ", publicationYear='" + publicationYear + '\'' + '}';
	}

}

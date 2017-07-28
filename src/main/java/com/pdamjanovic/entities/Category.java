package com.pdamjanovic.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Category extends AbstractEntity {

	@Column
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Book> books;

	public Category() {
	}

	public Category(Long id, String name) {
		this.setId(id);
		this.name = name;
		this.books = new HashSet<>();
	}

	public Category(Long id, String name, Set<Book> books) {
		this.setId(id);
		this.name = name;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Category{" + "id='" + super.getId() + '\'' + ", name='" + name + '\'' + '}';
	}
}

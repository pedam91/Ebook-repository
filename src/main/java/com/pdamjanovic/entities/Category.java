package com.pdamjanovic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Category extends AbstractEntity {

	@Column
	private String name;

	public Category() {
	}

	public Category(Long id, String name) {
		this.setId(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category{" + "id='" + super.getId() + '\'' + ", name='" + name + '\'' + '}';
	}
}

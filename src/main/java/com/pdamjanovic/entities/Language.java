package com.pdamjanovic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Language extends AbstractEntity {

	@Column
	private String name;

	public Language() {
	}

	public Language(Long id, String name) {
		this.setId(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

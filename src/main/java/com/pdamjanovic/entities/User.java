package com.pdamjanovic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class User extends AbstractEntity {

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String type;

	@ManyToOne
	private Category category;

	public User() {
	}

	public User(Long id, String name, String email, String password, String type,
			Category category) {
		this.setId(id);
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}

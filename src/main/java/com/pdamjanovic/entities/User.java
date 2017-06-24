package com.pdamjanovic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class User extends AbstractEntity {

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String userName;

	@Column
	private String password;

	@Column
	private String type;

	@ManyToOne
	private Category category;

	public User() {
	}

	public User(Long id, String firstName, String lastName, String userName, String password, String type,
			Category category) {
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.category = category;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

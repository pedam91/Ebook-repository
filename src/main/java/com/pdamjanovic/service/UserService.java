package com.pdamjanovic.service;

import com.pdamjanovic.entities.User;

public interface UserService {

	User save(User user);

	void delete(User user);

	void deleteById(Long id);

	void deleteAll();

	User findOne(Long id);

	Iterable<User> findAll();

	User findByEmail(String email);

	User findById(Long id);
}

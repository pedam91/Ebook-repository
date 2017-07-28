package com.pdamjanovic.service;

import com.pdamjanovic.entities.Category;

public interface CategoryService {

	Category save(Category category);

	void delete(Category category);

	void deleteById(Long id);

	void deleteAll();

	Category findOne(Long id);

	Iterable<Category> findAll();

}

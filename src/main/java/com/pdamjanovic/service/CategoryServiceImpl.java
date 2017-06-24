package com.pdamjanovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdamjanovic.entities.Category;
import com.pdamjanovic.repositories.jpa.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository repository;

	@Override
	public Category save(Category category) {
		return repository.save(category);
	}

	@Override
	public void delete(Category category) {
		repository.delete(category);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public Category findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Iterable<Category> findAll() {
		return repository.findAll();
	}

}

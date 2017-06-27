package com.pdamjanovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdamjanovic.entities.User;
import com.pdamjanovic.repositories.jpa.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public void delete(User user) {
		repository.delete(user);
	}

	@Override
	public void deleteById(Long id) {
		repository.delete(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public User findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Iterable<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return repository.findOne(id);
	}

}

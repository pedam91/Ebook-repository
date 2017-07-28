package com.pdamjanovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdamjanovic.entities.Language;
import com.pdamjanovic.repositories.jpa.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageRepository repository;

	@Override
	public Iterable<Language> findAll() {
		return repository.findAll();
	}

}

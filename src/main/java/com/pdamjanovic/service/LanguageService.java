package com.pdamjanovic.service;

import com.pdamjanovic.entities.Language;

public interface LanguageService {
	
	Iterable<Language> findAll();
}

package com.pdamjanovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdamjanovic.entities.BookFile;
import com.pdamjanovic.repositories.jpa.BookFileRepository;

@Service
public class BookFileServiceImpl implements BookFileService {

	@Autowired
	BookFileRepository repository;

	@Override
	public BookFile findById(Long bookFileId) {
		return repository.findOne(bookFileId);
	}

}

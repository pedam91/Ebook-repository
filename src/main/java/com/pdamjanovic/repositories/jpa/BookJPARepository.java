package com.pdamjanovic.repositories.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdamjanovic.entities.Book;

@Repository
public interface BookJPARepository extends CrudRepository<Book, Long> {

	Page<Book> findByAuthor(String author, Pageable pageable);

	List<Book> findByTitle(String title);

}

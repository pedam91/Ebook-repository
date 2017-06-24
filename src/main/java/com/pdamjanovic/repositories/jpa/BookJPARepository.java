package com.pdamjanovic.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdamjanovic.entities.Book;

@Repository
public interface BookJPARepository extends CrudRepository<Book, Long> {

}

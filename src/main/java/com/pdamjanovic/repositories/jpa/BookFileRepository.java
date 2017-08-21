package com.pdamjanovic.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdamjanovic.entities.BookFile;

@Repository
public interface BookFileRepository extends JpaRepository<BookFile, Long> {

}

package com.pdamjanovic.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdamjanovic.entities.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}

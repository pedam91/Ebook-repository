package com.pdamjanovic.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdamjanovic.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}

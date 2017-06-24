package com.pdamjanovic.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdamjanovic.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}

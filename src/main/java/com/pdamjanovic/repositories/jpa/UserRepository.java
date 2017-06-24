package com.pdamjanovic.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdamjanovic.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

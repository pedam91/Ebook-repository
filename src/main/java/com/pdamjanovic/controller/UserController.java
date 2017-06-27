package com.pdamjanovic.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pdamjanovic.entities.User;
import com.pdamjanovic.service.UserService;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@GetMapping(value = "/user/{id}")
	public String getUserById(@PathVariable("id") Long id, Map<String, Object> model) {

		logger.info("Get user by id:" + id);

		User user = userService.findById(id);
		model.put("user", user);

		return "user";
	}
}

package com.pdamjanovic.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pdamjanovic.entities.Category;
import com.pdamjanovic.service.CategoryService;
import com.pdamjanovic.service.UserService;
import com.pdamjanovic.util.LoggedInUser;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String homePage(Map<String, Object> model) {

		logger.debug("HOME PAGE METHOD - GET");

		List<Category> listOfCategories = null;

		Object loggedInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (loggedInUser != null && loggedInUser instanceof LoggedInUser) {
			Category usersCategory = userService.findById(((LoggedInUser) loggedInUser).getId()).getCategory();
			if (usersCategory != null) {
				listOfCategories = Arrays.asList(usersCategory);
			}
		}

		if (listOfCategories == null) {
			listOfCategories = (List<Category>) categoryService.findAll();
		}

		model.put("categories", listOfCategories);

		return "home";
	}

}

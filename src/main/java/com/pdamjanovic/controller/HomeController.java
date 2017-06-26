package com.pdamjanovic.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pdamjanovic.service.CategoryService;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CategoryService categoryService;

	@GetMapping("/")
	public String homePage(Map<String, Object> model) {
		logger.info("HOME PAGE METHOD - GET");

		model.put("categories", categoryService.findAll());
		model.put("message", "Hello visitor!");

		return "home";
	}

}

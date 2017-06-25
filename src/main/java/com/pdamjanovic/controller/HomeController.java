package com.pdamjanovic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/")
	public String homePage(Map<String, Object> model) {
		logger.info("HOME PAGE METHOD - GET");

		List<String> list = new ArrayList<String>();
		list.add("Sci-fi");
		list.add("Comics");
		list.add("Love");
		list.add("Horror");

		model.put("categories", list);
		model.put("message", "Hello visitor!");

		return "index";
	}

}

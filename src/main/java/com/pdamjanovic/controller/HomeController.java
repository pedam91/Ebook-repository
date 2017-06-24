package com.pdamjanovic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {

		logger.info("HOME PAGE METHOD - GET");
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("home");

		return modelView;
	}

}

package com.pdamjanovic.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pdamjanovic.entities.User;
import com.pdamjanovic.entities.UserRoles;
import com.pdamjanovic.service.CategoryService;
import com.pdamjanovic.service.UserService;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/user/{id}")
	public String getUserById(@PathVariable("id") Long userId, Map<String, Object> model) {

		logger.info("Get user by id:" + userId);

		User user = userService.findById(userId);
		model.put("user", user);

		return "user";
	}

	@GetMapping(value = "/user/{id}/edit")
	public String editUserById(@PathVariable("id") Long userId, Map<String, Object> model) {

		logger.info("Edit user by id:" + userId);

		User user = userService.findById(userId);
		model.put("user", user);

		model.put("categories", categoryService.findAll());
		return "user_edit";
	}

	@PostMapping(value = "/user/{id}")
	public String editUserByIdPost(@Validated @ModelAttribute("user") User user, BindingResult errors,
			@PathVariable("id") Long userId, Map<String, Object> model) {

		logger.info("Edit user (POST) by id:" + userId);

		if (errors.hasErrors()) {
			model.put("errorMessage", "Please correct form errors.");
			return "user_edit";
		}

		User updated = userService.save(user);
		model.put("user", updated);

		model.put("successMessage", "User info successfully updated.");

		return "user";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping(value = "/user/all")
	public String getAllUsers(Map<String, Object> model) {

		model.put("allUsers", userService.findAll());
		return "users";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping(value = "/user/{id}/delete")
	public String deleteUserById(@PathVariable("id") Long userId, Map<String, Object> model) {

		logger.info("Delete user by id:" + userId);
		userService.deleteById(userId);
		model.put("successMessage", "User id: [" + userId + "] succesfully deleted.");
		return getAllUsers(model);
	}
}

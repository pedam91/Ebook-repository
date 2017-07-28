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

import com.pdamjanovic.entities.Category;
import com.pdamjanovic.entities.UserRoles;
import com.pdamjanovic.service.CategoryService;

@Controller
public class CategoryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CategoryService categoryService;

	@GetMapping("/category/{id}")
	public String getCategoryPage(@PathVariable("id") Long categoryId, Map<String, Object> model) {

		Category category = categoryService.findOne(categoryId);
		model.put("category", category);

		return "category";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping(value = "/category/create")
	public String getCreateCategoryPage(Map<String, Object> model) {

		logger.debug("Create category page called");

		model.put("category", new Category());
		model.put("infoMessage", "Please enter information about new category:");

		return "category_edit";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@PostMapping(value = "/category/create")
	public String createCategory(@Validated @ModelAttribute("category") Category category, BindingResult errors,
			Map<String, Object> model) {

		if (errors.hasErrors()) {
			model.put("errorMessage", "Please correct form errors.");
			return "category_edit";
		}

		Category newCategory = categoryService.save(category);
		model.put("category", newCategory);
		model.put("successMessage", "Category info successfully created.");

		return "category";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping("/category/{id}/edit")
	public String getEditCategoryPage(@PathVariable("id") Long categoryId, Map<String, Object> model) {

		Category category = categoryService.findOne(categoryId);
		model.put("category", category);

		return "category";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@PostMapping("/category/{id}/edit")
	public String editCategory(@Validated @ModelAttribute("category") Category category, BindingResult errors,
			@PathVariable("id") Long categoryId, Map<String, Object> model) {

		if (errors.hasErrors()) {
			model.put("errorMessage", "Please correct form errors.");
			return "category_edit";
		}

		Category updatedCategory = categoryService.save(category);
		model.put("category", updatedCategory);
		model.put("successMessage", "Category info successfully updated.");

		return "category";
	}

	@Secured({ UserRoles.ROLE_ADMIN })
	@GetMapping("/category/{id}/delete")
	public String deleteCategoryById(@PathVariable("id") Long categoryId, Map<String, Object> model) {

		categoryService.deleteById(categoryId);
		model.put("successMessage", "Category id: [" + categoryId + "] successfully deleted");

		return "redirect:/";
	}
}

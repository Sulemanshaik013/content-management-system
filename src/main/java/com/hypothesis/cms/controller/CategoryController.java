package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hypothesis.cms.dto.CategoryDto;
import com.hypothesis.cms.model.Category;
import com.hypothesis.cms.service.ICategoryService;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	public CategoryController(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping()
	public Category createCategory(@RequestBody CategoryDto categoryDto) {
		return categoryService.createCategory(categoryDto);
	}

	@PutMapping("/{categoryId}")
	public Category updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
		return categoryService.updateCategory(categoryDto, categoryId);
	}

	@DeleteMapping("/{categoryId}")
	public Category deleteCategory(@PathVariable Long categoryId) {
		return categoryService.deleteCategoryByID(categoryId);
	}

	@GetMapping("/{categoryId}")
	public Category getCategoryById(@PathVariable Long categoryId) {
		return categoryService.getCategoryById(categoryId);
	}

	@GetMapping()
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

}

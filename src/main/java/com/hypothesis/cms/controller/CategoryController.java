package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
		Category createdCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategory, HttpStatus.OK);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
		Category updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Category> deleteCategory(@PathVariable Long categoryId) {
		Category deletedCategory = categoryService.deleteCategoryByID(categoryId);
		return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> allCategories = categoryService.getAllCategories();
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}

}

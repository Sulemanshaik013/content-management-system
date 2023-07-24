package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.CategoryDto;
import com.hypothesis.cms.model.Category;

public interface ICategoryService {

	Category createCategory(CategoryDto categoryDto);

	Category updateCategory(CategoryDto categoryDto, Long categoryId);

	Category deleteCategoryByID(Long categoryId);

	Category getCategoryById(Long categoryId);

	List<Category> getAllCategories();

}

package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.CategoryDto;

public interface ICategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

	CategoryDto deleteCategoryByID(Long categoryId);

	CategoryDto getCategoryById(Long categoryId);

	List<CategoryDto> getAllCategories();

}

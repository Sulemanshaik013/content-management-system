package com.hypothesis.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.CategoryDto;
import com.hypothesis.cms.exception.CustomException;
import com.hypothesis.cms.model.Category;
import com.hypothesis.cms.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setCategoryName(categoryDto.getName());
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(CategoryDto categoryDto, Long categoryId) {
		if (!categoryRepository.existsById(categoryId)) {
			throw new CustomException("there is no such comment with id :" + categoryId);
		}
		Optional<Category> category = categoryRepository.findById(categoryId);
		category.get().setCategoryName(categoryDto.getName());
		return categoryRepository.save(category.get());

	}

	@Override
	public Category deleteCategoryByID(Long categoryId) {
		if (!categoryRepository.existsById(categoryId)) {
			throw new CustomException("there is no such comment with id :" + categoryId);
		}
		Optional<Category> category = categoryRepository.findById(categoryId);
		categoryRepository.deleteById(categoryId);
		return categoryRepository.save(category.get());
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		if (!categoryRepository.existsById(categoryId)) {
			throw new CustomException("there is no such comment with id :" + categoryId);
		}
		Optional<Category> category = categoryRepository.findById(categoryId);
		return categoryRepository.save(category.get());
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}

package com.hypothesis.cms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.CategoryDto;
import com.hypothesis.cms.model.Category;
import com.hypothesis.cms.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category= new Category();
		category.setCategoryName(categoryDto.getName());
		return modelMapper.map(categoryRepository.save(category),CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Optional<Category> category= categoryRepository.findById(categoryId);
		category.get().setCategoryName(categoryDto.getName());
		return modelMapper.map(categoryRepository.save(category.get()),CategoryDto.class);
	
	}

	@Override
	public CategoryDto deleteCategoryByID(Long categoryId) {
		Optional<Category> category= categoryRepository.findById(categoryId);
		if(category.isPresent()) {
			categoryRepository.deleteById(categoryId);
		}
		return modelMapper.map(categoryRepository.save(category.get()),CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		Optional<Category> category= categoryRepository.findById(categoryId);
		if(category.isPresent()) {
			return modelMapper.map(categoryRepository.save(category.get()),CategoryDto.class);
		}
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		return modelMapper.map(categoryRepository.findAll(), new TypeToken<List<CategoryDto>>() {
		}.getType());
	}

	

}

package com.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Category;
import com.web.exception.RecordNotFoundException;
import com.web.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		List<Category> categoryList = categoryRepository.findAll();
		if (categoryList.size() > 0)
			return categoryList;
		else
			return new ArrayList<Category>();
	}

	@Override
	public Category addCategory(Category category) throws RecordNotFoundException {
		Optional<Category> categoryData = categoryRepository.findByName(category.getName());
		if (!categoryData.isPresent())
			return categoryRepository.save(category);
		else
			throw new RecordNotFoundException("Category already present!!");
	}

	@Override
	public Category updateCategory(Long id, Category category) throws RecordNotFoundException {
		Optional<Category> entity = categoryRepository.findById(id);
		if (entity.isPresent()) {
			category.setId(id);
			return categoryRepository.save(category);
		} else
			throw new RecordNotFoundException("No category found with id :" + id);
	}

	@Override
	public Category findByCategoryId(Long id) throws RecordNotFoundException {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return category.get();
		} else {
			throw new RecordNotFoundException("No category record exist for given id!!");
		}
	}

	@Override
	public Category findByCategoryName(String categoryName) throws RecordNotFoundException {
		Optional<Category> category = categoryRepository.findByName(categoryName);
		if (category.isPresent()) {
			return category.get();
		} else {
			throw new RecordNotFoundException("No category record exist for given category name!!");
		}
	}

	@Override
	public void deleteCategoryById(Long id) throws RecordNotFoundException {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			categoryRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No category record exist for given id");
		}
	}

}

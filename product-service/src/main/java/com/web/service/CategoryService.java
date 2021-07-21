package com.web.service;

import java.util.List;

import com.web.domain.Category;
import com.web.exception.RecordNotFoundException;

public interface CategoryService {

	public List<Category> getAllCategories();
	public Category addCategory(Category category) throws RecordNotFoundException;
	public Category updateCategory(Long id, Category category) throws RecordNotFoundException;
	public Category findByCategoryId(Long id) throws RecordNotFoundException;
	public Category findByCategoryName(String categoryName) throws RecordNotFoundException;
	public void deleteCategoryById(Long id) throws RecordNotFoundException;

}

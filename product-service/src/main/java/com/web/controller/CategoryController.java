package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.Category;
import com.web.exception.RecordNotFoundException;
import com.web.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) throws RecordNotFoundException {
		Category categoryData = categoryService.addCategory(category);
		return new ResponseEntity<Category>(categoryData, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id")Long id, @RequestBody Category category) throws RecordNotFoundException {
		Category categoryData = categoryService.updateCategory(id, category);
		return new ResponseEntity<Category>(categoryData, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> list = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getCategoryById/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id")Long id) throws RecordNotFoundException {
		Category categoryData = categoryService.findByCategoryId(id);
		return new ResponseEntity<Category>(categoryData, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getCategoryByName/{categoryName}")
	public ResponseEntity<Category> getCategoryByName(@PathVariable("categoryName") String categoryName) throws RecordNotFoundException {
		Category categoryData = categoryService.findByCategoryName(categoryName);
		return new ResponseEntity<Category>(categoryData, new HttpHeaders(), HttpStatus.OK);
	}
}

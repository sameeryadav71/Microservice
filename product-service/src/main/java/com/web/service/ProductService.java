package com.web.service;

import java.util.List;

import com.web.domain.Product;
import com.web.exception.RecordNotFoundException;

public interface ProductService {

	public Product createProduct(Product product) throws RecordNotFoundException;
	public Product getProductById(Long id) throws RecordNotFoundException;
	public Product updateProduct(Product productEntity, Long id) throws RecordNotFoundException;
	public List<Product> getAllProducts();
	public void deleteProduct(Long id) throws RecordNotFoundException;
	public Product getProductByName(String productName) throws RecordNotFoundException;
	public List<Product> findByCategory(Long categoryId);
}

package com.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.domain.Product;
import com.web.exception.RecordNotFoundException;
import com.web.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) throws RecordNotFoundException {
		Optional<Product> productData = productRepository.findByName(product.getName());
		if (!productData.isPresent())
			return productRepository.save(product);
		else
			throw new RecordNotFoundException("Product already present!!");
	}

	@Override
	public Product getProductById(Long id) throws RecordNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent())
			return product.get();
		else
			throw new RecordNotFoundException("No product record exist for given id!!");
	}

	@Override
	public Product updateProduct(Product product, Long id) throws RecordNotFoundException {
		Optional<Product> entity = productRepository.findById(id);
		if (entity.isPresent()) {
			product.setId(id);
			return productRepository.save(product);
		} else
			throw new RecordNotFoundException("No product found with id :" + id);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		if (productList.size() > 0)
			return productList;
		else
			return new ArrayList<Product>();
	}

	@Override
	public void deleteProduct(Long id) throws RecordNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent())
			productRepository.deleteById(id);
		else
			throw new RecordNotFoundException("No product record exist for given id");
	}

	@Override
	public Product getProductByName(String productName) throws RecordNotFoundException {
		Optional<Product> product = productRepository.findByName(productName);
		if (product.isPresent())
			return product.get();
		else
			throw new RecordNotFoundException("No product record exist for given category name!!");
	}

	@Override
	public List<Product> findByCategory(Long categoryId) {
		List<Product> productList = productRepository.findByCategory(categoryId);
		if (productList.size() > 0)
			return productList;
		else
			return new ArrayList<Product>();
	}

}

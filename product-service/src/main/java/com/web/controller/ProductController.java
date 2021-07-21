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
import com.web.domain.Product;
import com.web.exception.RecordNotFoundException;
import com.web.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws RecordNotFoundException {
		Product productData = productService.createProduct(product);
		return new ResponseEntity<Product>(productData, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id")Long id, @RequestBody Product product) throws RecordNotFoundException {
		Product productData = productService.updateProduct(product, id);
		return new ResponseEntity<Product>(productData, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id")Long id) throws RecordNotFoundException {
		Product productData = productService.getProductById(id);
		return new ResponseEntity<Product>(productData, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getProductByName/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable("productName") String productName) throws RecordNotFoundException {
		Product productData = productService.getProductByName(productName);
		return new ResponseEntity<Product>(productData, new HttpHeaders(), HttpStatus.OK);
	}
}

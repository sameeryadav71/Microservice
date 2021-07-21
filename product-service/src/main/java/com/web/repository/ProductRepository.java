package com.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select p from Product p where p.category.id=:categoryId")
	List<Product> findByCategory(@Param("categoryId")Long categoryId);
	
	Optional<Product> findByName(String name);
}

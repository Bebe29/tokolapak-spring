package com.cimb.tokolapak.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cimb.tokolapak.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {
	public Product findByProductName(String productName);
	
	@Query(value = "SELECT * FROM Product WHERE price > ?1 AND product_name = ?2", nativeQuery = true)
	public Iterable<Product> findProductByMinPrice(double minPrice, String productName);
	
	@Query(value = "SELECT * FROM Product WHERE price < :maxPrice AND product_name like %:productName%", nativeQuery = true)
	public Iterable<Product> findProductByMaxPrice(@Param("maxPrice") double maxPrice, @Param("productName") String namaProduk);
}

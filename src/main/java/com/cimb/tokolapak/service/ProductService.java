package com.cimb.tokolapak.service;

import java.util.Optional;

import com.cimb.tokolapak.entity.Product;

public interface ProductService {
	public  Iterable<Product> getProducts();
	
	public Optional<Product> getProductById(int id);
	
	public Product addProduct(Product product);
	
	public void deleteProductById(int id);
	
	public Product updateProduct(Product product);
	
	public Product getProductByProductName(String productName);
}

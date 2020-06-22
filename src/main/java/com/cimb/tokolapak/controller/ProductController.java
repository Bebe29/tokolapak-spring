package com.cimb.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.ProductRepo;
import com.cimb.tokolapak.entity.Product;
import com.cimb.tokolapak.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		return productService.getProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable() int id) {
		return productService.getProductById(id).get();
	}
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProductById(@PathVariable() int id) {
		this.productService.deleteProductById(id);;
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@GetMapping("/productName/{productName}")
	public Product getProductByProductName(@PathVariable() String productName) {
		return productService.getProductByProductName(productName);
	}
	
	@GetMapping("/products/custom")
	public Iterable<Product> customQueryGet(@RequestParam double minPrice, @RequestParam String productName) {
		return productRepo.findProductByMinPrice(minPrice, productName);
	}
	
	@GetMapping("/products/maxPrice")
	public Iterable<Product> maxPriceGet(@RequestParam double maxPrice, @RequestParam String namaProduk) {
		return productRepo.findProductByMaxPrice(maxPrice, namaProduk);
	}
}

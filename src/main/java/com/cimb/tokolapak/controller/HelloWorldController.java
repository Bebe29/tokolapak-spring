package com.cimb.tokolapak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin") //memberikan prefix untuk route
public class HelloWorldController {
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hellooo";
	}
	
	@GetMapping("/hello/{name}")
	public String helloName(@PathVariable() String name) {
		return "Hello " + name;
	}
}

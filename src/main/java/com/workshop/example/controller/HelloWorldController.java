package com.workshop.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(value = "/api/workshop/helloworld")
	public String helloWorld() {
		return "Hello World!!!";
	}
	
	@GetMapping(value = "/health")
	public String health() {
		return "i'm running";
	}
	
}

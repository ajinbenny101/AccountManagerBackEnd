package com.fdmgroup.springauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/verify")
public class LogInController {
	
	@GetMapping
	public String loggedIn() {
		return "OK";
	}
}

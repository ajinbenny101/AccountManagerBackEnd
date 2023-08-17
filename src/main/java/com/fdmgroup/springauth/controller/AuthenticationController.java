package com.fdmgroup.springauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.model.LoginResponseDTO;
import com.fdmgroup.springauth.model.RegistrationDTO;
import com.fdmgroup.springauth.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
//can be done in security configuration, maybe look at later
//allows requests from non spring applications, if using react
//would set to localhost:portnumber for whatever react is running on.
@CrossOrigin(origins="*")
public class AuthenticationController {
	
	//used to call register and log in methods
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
		return authenticationService.registerUser(body.getUsername(), body.getPassword());
	}
	
	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
		return authenticationService.loginUser(body.getUsername(), body.getPassword());
	}
}

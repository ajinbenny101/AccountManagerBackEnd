package com.fdmgroup.springauth.service;

import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.model.LoginResponseDTO;
import com.fdmgroup.springauth.model.Role;
import com.fdmgroup.springauth.repository.RoleRepository;
import com.fdmgroup.springauth.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {
	
	//create/retrieves users
	@Autowired
	private UserRepository userRepository;
	
	//retrieves roles to add to users
	@Autowired
	private RoleRepository roleRepository;
	
	//encodes password when registering user
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	//encodes password,
	//adds role to user
	//saves user
	public ApplicationUser registerUser(String username, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		
		Role userRole = roleRepository.findByAuthority("USER").get();
		
		Set<Role> authorities = new HashSet<>();
		authorities.add(userRole);
		
		return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
	}
	
	public LoginResponseDTO loginUser(String username, String password) {
		try {
			Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
			);
			
			String token = tokenService.generateJWT(auth);
			
			return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
		
		} catch (AuthenticationException e) {
			return new LoginResponseDTO(null, "");
		}
	}
}

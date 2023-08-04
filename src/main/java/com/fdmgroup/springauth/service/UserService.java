package com.fdmgroup.springauth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.model.Role;
import com.fdmgroup.springauth.repository.UserRepository;

//user details service specifies how we want to look for
//a user during authentication
@Service
public class UserService implements UserDetailsService{
	
	//not needed remove
	//TODO remove
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	//application user implements userdetails so can be returned
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("in the user details service");
		//if user exists returns new user
		return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user is not valid"));
	}

}

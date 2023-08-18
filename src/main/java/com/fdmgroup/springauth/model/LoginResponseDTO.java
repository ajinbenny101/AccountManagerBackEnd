package com.fdmgroup.springauth.model;

//what we return when a user logs in.
//user returned and the jwt returned
//this is returned from log in user method in
//the authentication service layer
public class LoginResponseDTO {
	private ApplicationUser user;
	private String jwt;
	
	public LoginResponseDTO() {
		super();
	}

	public LoginResponseDTO(ApplicationUser user, String jwt) {
		this.user = user;
		this.jwt = jwt;
	}

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
		
	
}

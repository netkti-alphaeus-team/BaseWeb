package com.ruokit.springcontext;

public class User {
	private String username;
	private String password;
	private String[] roles;

	public User(String username, String password, String... roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	// Getter and Setter methods
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String[] getRoles() {
		return roles;
	}	
}

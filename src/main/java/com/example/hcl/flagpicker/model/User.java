package com.example.hcl.flagpicker.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {

	
	private String username;
	private String password;
	private List<Role> roles;
	
	
	public User() {
		
	}
	public User(String username, String password, List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
	
	
	public static void main(String r[]) throws JsonParseException, JsonMappingException, IOException {
		
		Role admin = new Role("ADMIN", "admin role");
		Role userrole = new Role("USER", "user role");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(admin);
		roles.add(userrole);
		
		List<Role> roles1 = new ArrayList<Role>();
		roles1.add(userrole);
		
	    User user  = new User("ruhul", "pwd", roles);
	    User user1  = new User("john", "pwd", roles1);
	    User[] users  = new User[]{user,user1};
	    
//	    ObjectMapper mapper = new ObjectMapper();
//	    mapper.writeValue(
//	    	    new FileOutputStream("/tmp/user.json"), users);
		
	   // ObjectMapper mapper = new ObjectMapper();
		//List<User> u = mapper.readValue(new File("/tmp/user.json"), mapper.getTypeFactory().constructCollectionType(List.class, User.class));
		
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    System.out.println(passwordEncoder.encode("pwd"));
	    
	    
	}
	
	

	
	
	
	



}

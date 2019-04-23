package com.example.hcl.flagpicker.repository;


import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.hcl.flagpicker.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
public class UserRepositoryImpl implements UserRepository{

	public static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	List<User> users;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
    public UserRepositoryImpl() throws JsonParseException, JsonMappingException, IOException {
    	
    	
		logger.debug("Initilializing JSON Repository");
		ObjectMapper mapper = new ObjectMapper();
		users = mapper.readValue(new ClassPathResource("data/users.json").getFile(), mapper.getTypeFactory().constructCollectionType(List.class, User.class));
		System.out.println(users.size());
	}

	@Override
	public User findByUsername(String username) {
		for (User user : users) {
			if(user.getUsername().equals(username)) {
				//user.setPassword(passwordEncoder.encode(user.getPassword()));
				return user;
			}
		}
		
	    return null;
	}

}

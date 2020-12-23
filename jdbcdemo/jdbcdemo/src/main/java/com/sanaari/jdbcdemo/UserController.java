package com.sanaari.jdbcdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	JdbcUserRepository userRepo;

	// Home Page
	@GetMapping("/")
	public String welcome() {
		return "<html><body>" + "<h1>WELCOME</h1>" + "</body></html>";
	}

	// Get All User
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	// Get the user details by
	// ID
	@GetMapping("/users/{id}")
	public User getUserId(@PathVariable(value = "id") int id) {
		return userRepo.findUserById(id);
	}
	
	@PostMapping("/user") 
    @ResponseStatus(HttpStatus.CREATED) 
    public User addUser( 
        @RequestBody User user) 
    { 
        return userRepo.create(user); 
    } 
}

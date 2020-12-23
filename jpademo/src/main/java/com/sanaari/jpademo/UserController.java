package com.sanaari.jpademo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private JpaUserRepository userRepo;

	// Home Page
	@GetMapping("/")
	public String welcome() {
		return "<html><body>" + "<h1>WELCOME</h1>" + "</body></html>";
	}

	// Get All User
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userRepo.findAll();
	}

	// Get the user details by
	// ID
	@GetMapping("/users/{id}")
	public Users getUserId(@PathVariable(value = "id") int id) {
		return userRepo.findById(id);
	}

	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public Users addUser(@RequestBody Users user) {
		return userRepo.save(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable(value = "id") int id) {
		userRepo.deleteById(id);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody Users user, @PathVariable int id) {

		Optional<Users> usersRepo = Optional.ofNullable(userRepo.findById(id));

		if (!usersRepo.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);

		userRepo.save(user);

		return ResponseEntity.ok().build();
	}
}

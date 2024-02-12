package com.example.studybuddyapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/users/{id}")
//	https://springjavatutorial.medium.com/spring-security-login-rest-api-with-database-authentication-bb86f4a9f7b2
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		Optional<User> userData = userRepo.findById(id);
		
		if (userData.isPresent()) {
			User updateUser = userData.get();
//			updateUser.setEmail(user.getEmail());
//			updateUser.setPassword(user.getPassword());
			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			updateUser.setPhoneNumber(user.getPhoneNumber());
			updateUser.setInstitution(user.getInstitution());
			updateUser.setProgram(user.getProgram());
			
			return new ResponseEntity<>(userRepo.save(updateUser), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User newUser = userRepo.save(new User(
					user.getEmail(),
					user.getPassword(),
					user.getFirstName(),
					user.getLastName(),
					user.getPhoneNumber(),
					user.getProgram(),
					user.getInstitution())
			);
			
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

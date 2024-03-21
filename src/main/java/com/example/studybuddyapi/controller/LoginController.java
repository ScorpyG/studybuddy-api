package com.example.studybuddyapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studyapi.request.UserLoginRequest;
import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.UserRepository;

@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
		try {
			Optional<User> userData = userRepo.findByEmail(loginRequest.getUserEmail());
			
			if (userData.isPresent()) {
				String password = userData.get().getPassword();
				
				// TODO: BAD WAY handle authentication
				if (password.equals(loginRequest.getUserPassword())) {
					return new ResponseEntity<>(userData.get(), HttpStatus.OK);
				} else {
					// TODO: add error message object into response
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
				
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

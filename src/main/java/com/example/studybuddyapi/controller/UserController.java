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

import com.example.studybuddyapi.model.Pair;
import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.PairRepository;
import com.example.studybuddyapi.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PairRepository pairRepo;
	
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
			// Adding new user to the users table
			User newUser = userRepo.save(new User(
					user.getEmail(),
					user.getPassword(),
					user.getFirstName(),
					user.getLastName(),
					user.getPhoneNumber(),
					user.getProgram(),
					user.getInstitution())
			);
			
			System.out.println(newUser);
			
			// adding pairs for the new user into the pairs table
			Long test = newUser.getId();
		
			// iterate through each record in the users table
			for (long i = 1; i < test ; i++) {
				Optional<User> potentialPairUser = userRepo.findById(i);
				
				if (potentialPairUser.isPresent()) {
					User pairUser = potentialPairUser.get();
					double mqp = calculateMatchQualityScore(newUser, pairUser);
							
					pairRepo.save(new Pair(newUser, pairUser, mqp, false, false));
				}
			}
			
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// Calculate the MQP
	// TODO: separate into a class
	private double calculateMatchQualityScore(User mainUser, User interestUser) {
		double score = 0;
		
		String mainUserPrg = mainUser.getProgram().getCode();
		String interestUserPrg = interestUser.getProgram().getCode();
		
		String mainUserInstitution = mainUser.getInstitution().getInstitution_id();
		String interestUserInstitution = interestUser.getInstitution().getInstitution_id();
		
		
		// check for program of both users match add 3 points to the score
		if (mainUserPrg.equals(interestUserPrg)) {
			score += 3;
		}
		
		// check for institution of both users match add 2 points to the score
		if (mainUserInstitution.equals(interestUserInstitution)) {
			score += 2;
		}
		
		// TODO: check for matching hobbies
		
		return score;
	}
}

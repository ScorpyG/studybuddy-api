package com.example.studybuddyapi.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PairRepository pairRepo;
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> userData = userRepo.findById(id);
		
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		Optional<User> userData = userRepo.findById(id);
		
		try {
			if (userData.isPresent()) {
				User updateUser = userData.get();
				updateUser.setFirstName(user.getFirstName());
				updateUser.setLastName(user.getLastName());
				updateUser.setPhoneNumber(user.getPhoneNumber());
				updateUser.setInstitution(user.getInstitution());
				updateUser.setProgram(user.getProgram());
				
				return new ResponseEntity<>(userRepo.save(updateUser), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); 		
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User userInfo) {
		try {
			// Adding new user to the users table
			User newUser = userRepo.save(new User(userInfo.getEmail(), userInfo.getPassword(), userInfo.getFirstName(), 
					userInfo.getLastName(), userInfo.getPhoneNumber(), userInfo.getProgram(), userInfo.getInstitution(),
					userInfo.getHobbies()));
			
			// update the ENTIRE pair table every time new user sign up
			updateAllPairs();
			
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Calculate the MQP
	public double calculateMatchQualityScore(User mainUser, User interestUser) {
		double score = 0;
		
		String mainUserPrg = mainUser.getProgram().getCode();
		String interestUserPrg = interestUser.getProgram().getCode();
		
		String mainUserInstitution = mainUser.getInstitution().getInstitutionCode();
		String interestUserInstitution = interestUser.getInstitution().getInstitutionCode();
		String[] userHobbies = mainUser.getHobbies();
		String[] interestUserHobbies = interestUser.getHobbies();		
		
		// check for program of both users match add 3 points to the score
		if (mainUserPrg.equals(interestUserPrg)) {
			score += 3;
		}
		
		// check for institution of both users match add 2 points to the score
		if (mainUserInstitution.equals(interestUserInstitution)) {
			score += 2;
		}
		
		
		for (int i = 0; i < userHobbies.length; i++) {
			for (int j = 0; j < interestUserHobbies.length; j++) {
				if (userHobbies[i].replaceAll("\\s", "").equalsIgnoreCase(interestUserHobbies[j])) {
					score += 0.1;
				}
			}
		}
		
		return score;
	}
	
	// TODO: this required heavy optimization 
	public void updateAllPairs() {
		ArrayList<User> userList = new ArrayList<User>();
		ArrayList<Pair> pairList = new ArrayList<Pair>();
		
		userRepo.findAll().forEach(userList::add);
		
		int userNum = userList.size() - 1;
		
		// iterate through the current user list
		for (int i = 0; i < userList.size(); i++) {
			int k;
			
			// current user isn't the last user (exclude the newest user)
			if (i != userNum) {
				k = userNum; // pointer refer to the newest user
			} else {
				k = 0;
			}
			
			// iterate through the list and create all new pair associated with the new user while maintain the old records
			for (int j = k; j < userList.size(); j++) {
				if (i != j) {
					double calculatedMqp = calculateMatchQualityScore(userList.get(i), userList.get(j));
					// By default both blocked and interested is false since no action been perform.
					Pair newPair = new Pair(userList.get(i), userList.get(j), calculatedMqp, false, false);
					pairList.add(newPair);
				}
			}
		}
		pairRepo.saveAll(pairList);
	}
}

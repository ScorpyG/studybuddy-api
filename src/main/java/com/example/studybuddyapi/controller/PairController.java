package com.example.studybuddyapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.model.Pair;
import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.PairRepository;
import com.example.studybuddyapi.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class PairController {
	@Autowired
	PairRepository pairRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/pairs/{id}")
	public ResponseEntity<List<User>> getListOfStudyBuddies(@PathVariable("id") long id) {
		try {
			Optional<User> userData = userRepo.findById(id);
			List<Pair> pairs = new ArrayList<>();
			List<User> curatedList = new ArrayList<>();
			
			
			if (userData.isPresent()) {
				pairRepo.findByUserId(id).forEach(pairs::add);
				
				// O(n)
				pairs.removeIf(pair -> pair.isBlocked() || pair.isPaired());
				
				// O(n log n)
				pairs.sort((Pair pair1, Pair pair2) -> { 
					if (pair1.getMqp() > pair2.getMqp()) {
						return -1; // USER with higher MQP should be at the top of the list
					} else if (pair1.getMqp() < pair2.getMqp()) {
						return 1; // USER with lesser MQP should be at the bottom of the list
					} else {
						return 0;
					}
				});
				
				// O(n)
				for (Pair pair : pairs) {
					curatedList.add(pair.getInterestUser());
				}
				
				return new ResponseEntity<>(curatedList, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

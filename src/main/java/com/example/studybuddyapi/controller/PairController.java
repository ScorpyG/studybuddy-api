package com.example.studybuddyapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.model.Pair;
import com.example.studybuddyapi.model.User;
import com.example.studybuddyapi.repositories.PairRepository;
import com.example.studybuddyapi.repositories.UserRepository;

@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class PairController {
	@Autowired
	PairRepository pairRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/pairs/{userId}")
	public ResponseEntity<List<User>> getListOfStudyBuddies(@PathVariable("userId") long id) {
		try {
			Optional<User> userData = userRepo.findById(id);
			List<Pair> pairs = new ArrayList<>();
			List<User> curatedList = new ArrayList<>();
			
			
			if (userData.isPresent()) {
				pairRepo.findByUserId(id).forEach(pairs::add);
				
				// O(n) This line check for pair of the main user and associated user
				// that being blocked or interested then remove it from the curated list
				pairs.removeIf(pair -> pair.isBlocked() || pair.isInterested());
				
				// O(n log n) Sort the user with highest to lowest mqp(match quality point)
				pairs.sort((Pair pair1, Pair pair2) -> { 
					if (pair1.getMqp() > pair2.getMqp()) {
						return -1;
					} else if (pair1.getMqp() < pair2.getMqp()) {
						return 1;
					} else {
						return 0;
					}
				});
				
				// add the all the interest users to the curated list O(n)
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
	
	@PutMapping("/pairs/{pairId}")
	public ResponseEntity<Pair> updatePairData(@PathVariable("pairId") int id, @RequestBody Pair pair) {
		try {
			Optional<Pair> pairData = pairRepo.findById(id);
			
			if (pairData.isPresent()) {
				Pair currentPair = pairData.get();
				currentPair.setPaired(pair.isInterested());
				currentPair.setBlocked(pair.isBlocked());
				
				return new ResponseEntity<>(pairRepo.save(currentPair), HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/paired/{userId}")
	public ResponseEntity<List<User>> getListOfPairedStudyBuddies(@PathVariable("userId") long id) {
		try {
			Optional<User> userData = userRepo.findById(id);
			List<Pair> studybuddies = new ArrayList<>();
			List<User> matchedUsers = new ArrayList<>();
			
			if (userData.isPresent()) {
				pairRepo.findAllMatchedPairs(id).forEach(studybuddies::add);
				
				for (Pair matchedPair : studybuddies) {
					matchedUsers.add(matchedPair.getInterestUser());
				}
				
				return new ResponseEntity<>(matchedUsers, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

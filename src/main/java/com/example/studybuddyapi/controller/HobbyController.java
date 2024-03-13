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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.model.Hobby;
import com.example.studybuddyapi.repositories.HobbyRepository;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api")
public class HobbyController {
	@Autowired
	HobbyRepository hobbyRepo;
	
	
	@GetMapping("/hobbies/{id}")
	public ResponseEntity<Hobby> getHobbyById(@PathVariable("id") Long id) {
		Optional<Hobby> hobby = hobbyRepo.findById(id);
		if (hobby.isPresent()) {
			return new ResponseEntity<>(hobby.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping("/hobbies")
    public ResponseEntity<List<Hobby>> getAllHobbies(@RequestParam(name = "category", required = false) String category) {
        try {
            List<Hobby> hobbies = new ArrayList<>();

            if (category == null) {
                hobbyRepo.findAll().forEach(hobbies::add);
            } else {
            	// hobby.toLowerCase().contains(category.trim());
                hobbyRepo.findByCategoryContainingIgnoreCase(category).forEach(hobbies::add);
            }

            if (hobbies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(hobbies, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

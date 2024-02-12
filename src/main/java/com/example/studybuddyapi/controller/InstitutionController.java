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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.model.Institution;
import com.example.studybuddyapi.repositories.IntitutionRepository;

@RestController
@RequestMapping("/api")
public class InstitutionController {
	@Autowired
	IntitutionRepository institutionRepo;
	
	@GetMapping("/institutions/{id}")
	public ResponseEntity<Institution> getInstitutionById(@PathVariable("id") String id) {
		Optional<Institution> institution = institutionRepo.findById(id);
		
		if (institution.isPresent()) {
			return new ResponseEntity<>(institution.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/institutions")
	public ResponseEntity<List<Institution>> getInstitutions(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "country", required = false) String country
	) {
		try {
			List<Institution> institutions = new ArrayList<Institution>();
			
			if (name == null | city == null || state == null | country == null) {
				institutionRepo.findAll().forEach(institutions::add);
			} else {
				if (country != null) {
					institutionRepo.findByCountry(country).forEach(institutions::add);
				} else if (state != null) {
					institutionRepo.findByState(state).forEach(institutions::add);
				} else if (city != null) {
					institutionRepo.findByCity(city).forEach(institutions::add);
				} else if (name != null) {
					institutionRepo.findByName(name).forEach(institutions::add);
				} else {
//					Find a way to create a dynamic API endpoint
//					https://www.baeldung.com/spring-cassandra-query-in-clause
//					https://www.baeldung.com/jpa-and-or-criteria-predicates
				}
			}
			
			if (institutions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(institutions, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.example.studybuddyapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/institutions")
	public ResponseEntity<List<Institution>> getAllInstitutions(
			@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "city", required = false) String city
	) {
		try {
			List<Institution> institutions = new ArrayList<Institution>();
			
			if (city == null && state == null && country == null) {
				institutionRepo.findAll().forEach(institutions::add);
			} else if (country != null) {
				institutionRepo.findByCountry(country).forEach(institutions::add);
			} else if (state != null) {
				institutionRepo.findByState(state).forEach(institutions::add);
			} else if (city != null) {
				institutionRepo.findByCity(city).forEach(institutions::add);
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

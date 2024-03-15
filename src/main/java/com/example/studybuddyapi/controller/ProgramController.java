package com.example.studybuddyapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.model.Program;
import com.example.studybuddyapi.repositories.ProgramRepository;

@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class ProgramController {
	@Autowired
	ProgramRepository programRepo;	
	
	@GetMapping("/programs")
	public ResponseEntity<List<Program>> getAllPrograms(
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "code", required = false) String code) 
	{
		try {
			List<Program> programs = new ArrayList<Program>();
			
			if (title == null && code == null) {
				programRepo.findAll().forEach(programs::add);
			} else if (title != null) {
				programRepo.findByTitle(title).forEach(programs::add);
			} else if (code != null) {
				programRepo.findByCode(code).forEach(programs::add);
			} else {
				programRepo.findByTitle(title).forEach(programs::add);
			}
			
			if (programs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(programs, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

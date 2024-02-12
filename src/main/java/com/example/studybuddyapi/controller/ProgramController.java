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

import com.example.studybuddyapi.model.Program;
import com.example.studybuddyapi.repositories.ProgramRepository;

@RestController
@RequestMapping("/api")
public class ProgramController {
	@Autowired
	ProgramRepository programRepo;
	
	@GetMapping("/programs/{id}")
	public ResponseEntity<Program> getProgramById(@PathVariable("id") String id) {
		Optional<Program> program = programRepo.findById(id);
		
		if (program.isPresent()) {
			return new ResponseEntity<>(program.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping("/programs")
	public ResponseEntity<List<Program>> getAllPrograms(@RequestParam(name = "title", required = false) String title) {
		try {
			List<Program> programs = new ArrayList<Program>();
			
			if (title == null) {
				programRepo.findAll().forEach(programs::add);
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

package com.example.studybuddyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studybuddyapi.repositories.PairRepository;

@RestController
@RequestMapping("/api")
public class PairController {
	@Autowired
	PairRepository pairRepo;
	
//	@GetMappings("/pairs")
//	public ResponseEntity<List<Pair>> getAllPairs
}

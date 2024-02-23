package com.example.studybuddyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studybuddyapi.model.Pair;

public interface PairRepository extends JpaRepository<Pair, Integer> {
	
}

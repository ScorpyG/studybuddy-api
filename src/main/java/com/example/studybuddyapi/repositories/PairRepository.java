package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studybuddyapi.model.Pair;

public interface PairRepository extends JpaRepository<Pair, Integer> {
	List<Pair> findByUserId(long id);
	
	@Query(value = "SELECT p FROM Pair p WHERE p.user = :id AND p.interested = true")
	List<Pair> findByInterestUserId(long id);
}

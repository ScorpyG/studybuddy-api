package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studybuddyapi.model.Pair;

public interface PairRepository extends JpaRepository<Pair, Integer> {
	List<Pair> findByUserId(long id);

	// TODO: DO NOT touch this query
	@Query(value = "SELECT p1.* from Pairs p1 JOIN Pairs p2 "
			+ "ON p1.user_id = p2.interest_user_id "
			+ "AND p1.interest_user_id = p2.user_id "
			+ "WHERE p1.user_id = :userId "
			+ "AND p1.interested = true "
			+ "AND p2.interested = true ",
			nativeQuery = true)
	List<Pair> findAllMatchedPairs(long userId);
}

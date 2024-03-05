package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studybuddyapi.model.Pair;

public interface PairRepository extends JpaRepository<Pair, Integer> {
	List<Pair> findByUserId(long id);
	
//	@Query(value = "SELECT p FROM Pair p WHERE p.user = :id AND p.interested = true")
//	List<Pair> findByInterestUserId(long id);

	
	@Query(value = "SELECT p1.* from Pairs p1 JOIN Pairs p2 "
			+ "ON p1.user_id = p2.user_id "
			+ "WHERE p1.interest_user_id = p2.interest_user_id "
			+ "AND p1.interested = true "
			+ "AND p2.interested = true "
			+ "AND p1.user_id = :userId",
			nativeQuery = true)
	List<Pair> findAllMatchedPairs(long userId);
}

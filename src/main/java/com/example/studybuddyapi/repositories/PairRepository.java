package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.studybuddyapi.model.Pair;

@Transactional
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
	
	
	@Query(value = "SELECT * FROM Pairs "
			+ "WHERE user_id = :userId "
			+ "OR interest_user_id = :userId", 
			nativeQuery = true)
	List<Pair> findAllPairsContainingUserById(long userId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Pairs SET mqp = :newMqp WHERE pair_id = :pairId", nativeQuery = true)
	void updatePairContainingUserId(int pairId, double newMqp);
}

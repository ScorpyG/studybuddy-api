package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studybuddyapi.model.Pair;

public interface PairRepository extends JpaRepository<Pair, Integer> {
	List<Pair> findByUserId(long id);
}

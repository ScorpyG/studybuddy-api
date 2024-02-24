package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studybuddyapi.model.Hobby;



public interface HobbyRepository extends JpaRepository <Hobby, Long>{
	List<Hobby> findByCategory(String category);
}

package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studybuddyapi.model.Institution;

public interface IntitutionRepository extends JpaRepository <Institution, Long>{
	List<Institution> findByCountry(String country);
	List<Institution> findByState(String state);
	List<Institution> findByCity(String city);
	List<Institution> findByName(String name);
}

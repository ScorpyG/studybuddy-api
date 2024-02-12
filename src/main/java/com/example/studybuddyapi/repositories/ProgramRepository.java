package com.example.studybuddyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studybuddyapi.model.Program;

public interface ProgramRepository extends JpaRepository<Program, String> {
	List<Program> findByTitle(String title);
	List<Program> findByCode(String code);
}

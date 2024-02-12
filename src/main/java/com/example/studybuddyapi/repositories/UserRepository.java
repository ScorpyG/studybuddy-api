package com.example.studybuddyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studybuddyapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	Boolean existsByEmail(String email);
}

package com.example.studybuddyapi.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "programs")
public class Program {
	@Id
	private String code;
	
	@Column(name = "title")
	private String title;
	
	@OneToMany(mappedBy = "program")
	private List<User> users;

	// Constructor
	public Program() {}
	public Program(String code, String title) {
		this.code = code;
		this.title = title;
	}

	// Setters
	public void setCode(String code) {
		this.code = code;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	// Getters
	public String getCode() {
		return code;
	}
	public String getTitle() {
		return title;
	}
}

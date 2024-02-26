package com.example.studybuddyapi.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "programs")
public class Program {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column (name = "program_id", unique = true)
	private String code;
	
	@Column(name = "title")
	private String title;

	@OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> users = new HashSet<>();

	// Constructor
	public Program() {}
	
	public Program(String code, String title) {
		this.code = code;
		this.title = title;
	}

	// Setters
	public void setId(long id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	// Getters
	public Long getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getTitle() {
		return title;
	}
}

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
@Table (name = "institution")
public class Institution {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (name = "institution_id", unique = true)
	private String institutionCode;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "address")
	private String address;
	
	@Column (name = "city")
	private String city;
	
	@Column (name = "state")
	private String state;
	
	@Column (name = "country")
	private String country;
	
	@OneToMany(mappedBy="institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> users = new HashSet<>();
	
	// Constructor
	public Institution() {}
	
	public Institution(String institutionCode, String name, String address, String city, String state, String country) {
		this.institutionCode = institutionCode;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	// Getters
	public long getId() {
		return id;
	}
	public String getInstitutionCode() {
		return institutionCode;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	
	// Setters
	public void setId(long id) {
		this.id = id;
	}
	public void setInstitution_id(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}

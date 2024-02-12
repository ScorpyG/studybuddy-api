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
@Table (name = "institution")
public class Institution {
	@Id
	private String institution_id;
	
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
	
	@OneToMany(mappedBy = "institution")
	private List<User> users;
	
	// Constructor
	public Institution() {}
	
	public Institution(String id, String name, String address, String city, String state, String country) {
		this.institution_id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	// Getters
	public String getInstitution_id() {
		return institution_id;
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
	public void setInstitution_id(String institution_id) {
		this.institution_id = institution_id;
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

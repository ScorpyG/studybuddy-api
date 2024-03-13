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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "phone_number")
	private String phoneNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "code")
	private Program program;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_id")
	private Institution institution;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_hobby_mapping", 
		joinColumns = { @JoinColumn(name = "user_id")},
		inverseJoinColumns = { @JoinColumn(name = "hobby_id")}
	)
	private Set<Hobby> hobbies = new HashSet<Hobby>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Pair> pairs = new HashSet<>();
	
	// Constructor
	public User() {}
	public User(String email, String password, String firstName, String lastName, String phoneNumber, Program program, Institution institution, Set<Hobby> hobbies) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.program = program;
		this.institution = institution;
		this.hobbies = hobbies;
	}
	
	// Getters
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public Program getProgram() {
		return program;
	}
	public Institution getInstitution() {
		return institution;
	}
	public Set<Hobby> getHobbies() {
		return hobbies;
	}
	public Set<Pair> getPairs() {
		return pairs;
	}
	
	// Setters
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public void addHobbies(Hobby hobby) {
		this.hobbies.add(hobby);
		hobby.getUser().add(this);
	}
	public void removeHobby(Hobby hobby) {
		this.hobbies.remove(hobby);
		hobby.getUser().remove(this);
	}
	
	public void setPairs(Set<Pair> pairs) {
		this.pairs = pairs;
	}
}

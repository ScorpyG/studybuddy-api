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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hobbies")
public class Hobby {
    @Id
    @Column(name = "hobby_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;
    
    @ManyToMany(mappedBy = "hobbies", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIgnore
    private Set<User> user = new HashSet<User>();

    // Constructor
    public Hobby() {} 
    public Hobby(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getUser() {
		return user;
	}
    
	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
package com.example.studybuddyapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pairs")
public class Pair {
	@Id
	@Column(name = "pair_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int pairId;
	
	@Column (name = "mqp")
	private double mqp;
	
	@Column (name = "interested")
	private boolean interested;
	
	@Column (name = "blocked")
	private boolean blocked;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "interest_user_id")
	@JsonIgnore
	private User interestUser;
	
	// Constructor
	public Pair() {}
	public Pair(User user, User interestUser, double mqp, boolean interested, boolean blocked) {
		this.user = user;
		this.interestUser = interestUser;
		this.mqp = mqp;
		this.interested = interested;
		this.blocked = blocked;
	}
	
	// Getters
	public int getPairId() {
		return pairId;
	}
	public double getMqp() {
		return mqp;
	}
	public boolean isInterested() {
		return interested;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public User getUser() {
		return user;
	}
	public User getInterestUser() {
		return interestUser;
	}
	
	// Setters
	public void setPairId(int pairId) {
		this.pairId = pairId;
	}
	public void setMqp(double mqp) {
		this.mqp = mqp;
	}
	public void setPaired(boolean interested) {
		this.interested = interested;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setInterestUser(User interestUser) {
		this.interestUser = interestUser;
	}
}

package com.example.studybuddyapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pairs")
public class Pair {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int pairId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "interested_id", nullable = false)
	private User interestUser;
	
	@Column (name = "mqp")
	private double mqp;
	
	@Column (name = "paired")
	private boolean paired;
	
	@Column (name = "blocked")
	private boolean blocked;
	
	// Constructor
	public Pair() {}
	public Pair(User user, User interestUser, double mqp, boolean paired, boolean blocked) {
		this.user = user;
		this.interestUser = interestUser;
		this.mqp = mqp;
		this.paired = paired;
		this.blocked = blocked;
	}
	
	// Getters
	public User getUser() {
		return user;
	}
	public User getInterestUser() {
		return interestUser;
	}
	public double getMqp() {
		return mqp;
	}
	public boolean isPaired() {
		return paired;
	}
	public boolean isBlocked() {
		return blocked;
	}
	
	// Setters
	public void setUser(User user) {
		this.user = user;
	}
	public void setInterestUserId(User interestUser) {
		this.interestUser = interestUser;
	}
	public void setMqp(double mqp) {
		this.mqp = mqp;
	}
	public void setPaired(boolean paired) {
		this.paired = paired;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}

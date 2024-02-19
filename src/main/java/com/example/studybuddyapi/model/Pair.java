package com.example.studybuddyapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pairs")
public class Pair {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int pairId;

	@Column (name = "user_id")
	private Long userId;
	
	@Column (name = "interested_user_id")
	private Long interestUserId;
	
	@Column (name = "mqp")
	private double mqp;
	
	@Column (name = "paired")
	private boolean paired;
	
	@Column (name = "blocked")
	private boolean blocked;
	
	// Constructor
	public Pair() {}
	public Pair(Long userId, Long interestUserId, double mqp, boolean paired, boolean blocked) {
		this.userId = userId;
		this.interestUserId = interestUserId;
		this.mqp = mqp;
		this.paired = paired;
		this.blocked = blocked;
	}
	
	// Getters
	public Long getUserId() {
		return userId;
	}
	public Long getInterestUserId() {
		return interestUserId;
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
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setInterestUserId(Long interestUserId) {
		this.interestUserId = interestUserId;
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

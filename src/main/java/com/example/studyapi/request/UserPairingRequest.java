package com.example.studyapi.request;

public class UserPairingRequest {
	private Long userId;
	private Long interestUserId;
	private boolean blocking;
	private boolean interesting;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getInterestUserId() {
		return interestUserId;
	}
	public void setInterestUserId(Long interestUserId) {
		this.interestUserId = interestUserId;
	}
	public boolean isBlocking() {
		return blocking;
	}
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}
	public boolean isInteresting() {
		return interesting;
	}
	public void setInteresting(boolean interesting) {
		this.interesting = interesting;
	}
	

}

package com.example.studyapi.request;

public class UserLoginRequest {
	private String userEmail;
	
	private String userPassword;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPassword() {
		return userPassword; 
	}
	public void setUserPassword(String password) {
		this.userPassword = password;
	}
}

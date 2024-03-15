package com.example.studyapi.request;

import com.example.studybuddyapi.model.Institution;
import com.example.studybuddyapi.model.Program;

public class UserUpdateRequest {
	private String userFirstName;
	
	private String userLastName;
	
	private String userPhoneNumber;
	
	private Program userProgram;
	
	private Institution userInstitution;
	
	private String[] userHobbies;

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public Program getUserProgram() {
		return userProgram;
	}

	public void setUserProgram(Program userProgram) {
		this.userProgram = userProgram;
	}

	public Institution getUserInstitution() {
		return userInstitution;
	}

	public void setUserInstitution(Institution userInstitution) {
		this.userInstitution = userInstitution;
	}

	public String[] getUserHobbies() {
		return userHobbies;
	}

	public void setUserHobbies(String[] userHobbies) {
		this.userHobbies = userHobbies;
	}
}

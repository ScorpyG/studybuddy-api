package payload;

public class SignInDto {
	private String email;
	private String password;
	
	// Constructor
	public SignInDto() {}
	public SignInDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	// Getters
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	// Setters
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}

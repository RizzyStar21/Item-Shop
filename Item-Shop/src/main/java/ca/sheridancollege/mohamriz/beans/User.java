package ca.sheridancollege.mohamriz.beans;

public class User {

	private Long userId;
	private String username;
	private String encryptedPassword;
	
	public User() {}
	
	public User(Long userId, String username, String encryptedPassword) {
		this.userId = userId;
		this.username = username;
		this.encryptedPassword = encryptedPassword;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	
	
}

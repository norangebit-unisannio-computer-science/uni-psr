package model;

public class User {
	private String firstName;
	private String lastName;
	private String sex;
	private String email;
	private String username;
	private int password;

	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getSex() { return sex; }
	public String getEmail() { return email; }
	public String getUsername() { return username; }
	public int getPassword() { return password; }
	
	
	public void setLastName(String ln){
		lastName = ln;
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(int password){
		this.password = password;
	}
	public void setFirstName(String fn) {
		firstName = fn;
	}
}

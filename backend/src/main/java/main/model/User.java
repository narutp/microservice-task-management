package main.model;

public class User {
	private String name, password;
	
	public String getName () {
		return this.name;
	}
	
	public String getPassword () {
		return this.password;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void setPassword (String pass) {
		this.password = pass;
	}
}

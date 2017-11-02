package main.model;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	private String idUser;
	private String name, birthdate, email, mobilePhone, idDepartment, idPosition, username, password;
	private boolean taskAuthority;
	
	public String getIdUser() {
		return idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(String idDepartment) {
		this.idDepartment = idDepartment;
	}
	public String getIdPosition() {
		return idPosition;
	}
	public void setIdPosition(String idPosition) {
		this.idPosition = idPosition;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isTaskAuthority() {
		return taskAuthority;
	}
	public void setTaskAuthority(boolean taskAuthority) {
		this.taskAuthority = taskAuthority;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

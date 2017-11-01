package main.model;

import org.springframework.data.annotation.Id;

public class Department {
	
	@Id
	private String idDepartment;
	private String name;
	
	public String getIdDepartment() {
		return idDepartment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}

package main.model;

import org.springframework.data.annotation.Id;

public class Position {
	
	@Id
	private String idPosition;
	
	private String name;
	
	public String getIdPosition() {
		return idPosition;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

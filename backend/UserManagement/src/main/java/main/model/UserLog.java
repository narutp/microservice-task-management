package main.model;

import org.springframework.data.annotation.Id;

public class UserLog {
	
	@Id
	private String idUserLog;
	private String idUser;
	private String [] idTaskCard;
	
	public String getIdUserLog() {
		return idUserLog;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String[] getIdTaskCard() {
		return idTaskCard;
	}
	public void setIdTaskCard(String[] idTaskCard) {
		this.idTaskCard = idTaskCard;
	}
}

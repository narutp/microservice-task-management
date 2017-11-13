package main.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class UserHistory {
	
	@Id
	private String idUserHistory;
	private String idUser;
	private List<String> idProjectCards;
	
	public String getIdUserHistory() {
		return idUserHistory;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public List<String> getIdProjectCards() {
		return idProjectCards;
	}
	public void setIdProjectCards(List<String> idProjectCards) {
		this.idProjectCards = idProjectCards;
	}
}

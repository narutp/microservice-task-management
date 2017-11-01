package main.model;

import org.springframework.data.annotation.Id;

public class Request {

	@Id
	private String idRequest;
	private String idCard, type, reason, date, idRequester;
	public String getIdRequest() {
		return idRequest;
	}
	public void setIdRequest(String idRequest) {
		this.idRequest = idRequest;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIdRequester() {
		return idRequester;
	}
	public void setIdRequester(String idRequester) {
		this.idRequester = idRequester;
	}
	
	
}
